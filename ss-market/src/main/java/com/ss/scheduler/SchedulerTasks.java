package com.ss.scheduler;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ss.app.entity.Member;
import com.ss.app.entity.Notification;
import com.ss.app.entity.RewardTransaction;
import com.ss.app.entity.SSConfiguration;
import com.ss.app.model.NotificationRepository;
import com.ss.app.model.RewardTransactionRepository;
import com.ss.app.model.SSConfigRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.MemberRewardTree;
import com.ss.utils.MemberLevel;

@Component
public class SchedulerTasks {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SSConfigRepository ssConfigRepository;

	@Autowired
	RewardTransactionRepository rewardTransactionRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	static RestTemplate restTemplate = new RestTemplate();
	private static final String sApiKey = "7dUGepGKDjLMq7Lagvs+rKgmztfdB/PbstEPi/oe/nk=";
	private static final String sClientId = "dfa59504-961e-4710-a823-8d3556e57d5c";
	private static final String sSenderId = "SSMRKT";
	private static final boolean is_Unicode = true;
	private static final boolean is_Flash = false;

	@Scheduled(cron = "*/2 * * * * *")
	@Async
	private void smsTrigger() {
		try {
			// System.out.println("SMS TRIGGER");
			sendSMS();
			// System.out.println("SMS END");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0 0 * * ?")
	private void reward() {
		System.out.println("Start Daily Reward!");
		List<Member> memberList = userRepository.getActiveMembersOnly();
		dailyReward(memberList);
		activeReward(memberList);
		System.out.println("End Daily Reward!");
	}

	private void dailyReward(List<Member> memberList) {
		List<SSConfiguration> levels = ssConfigRepository.getRewardLevels();
		Map<String, Double> map = new HashMap<>();

		for (SSConfiguration ssConfiguration : levels) {
			map.put(ssConfiguration.getCode(), ssConfiguration.getValue());
		}
		for (Member member : memberList) {
			MemberRewardTree memberRewardTree = new MemberRewardTree();
			memberRewardTree.setId(member.getId());
			recursionTree(memberRewardTree, member.getReferencecode(), member.getId());
			Double awdVal = MemberLevel.process(memberRewardTree, map, rewardTransactionRepository,
					getActiveDirectCount(member));
			if (awdVal > 0) {
				member.setWalletBalance(member.getWalletBalance() + awdVal.longValue());
				userRepository.save(member);
			}
		}
	}
	
	private void activeReward(List<Member> memberList) {
		Optional<SSConfiguration> configVal = ssConfigRepository.findById("1115");
		if(!configVal.isEmpty()) {
			for (Member member : memberList) {
				Double rewardVal = configVal.get().getValue();
				rewardTransactionRepository.save(prepareRewarTransaction(member.getId(), rewardVal));
				if (rewardVal > 0) {
					member.setWalletBalance(member.getWalletBalance() + rewardVal.longValue());
					userRepository.save(member);
				}
			}
		}
	}
	
	private RewardTransaction prepareRewarTransaction(String memberId, Double rewardVal) {
		RewardTransaction reward = new RewardTransaction();
		reward.setPoint(rewardVal);
		reward.setOrderNumber(100002L);
		reward.setRewardedMember(memberId);
		return reward;
	}

	private int getActiveDirectCount(Member member) {
		int activeDirectCnt = -1;
		List<Member> child = userRepository.findByReferedby(member.getReferencecode());
		for (Member chMember : child) {
			if (chMember.getActive_days() != null && chMember.getActive_days().isAfter(LocalDateTime.now())) {
				++activeDirectCnt;
			}
		}
		return activeDirectCnt;
	}

	private List<String> recursionTree(MemberRewardTree memberRewardTree, String basekeyCode, String memberId) {
		List<Member> child = userRepository.findByReferedby(basekeyCode);
		List<String> c = new ArrayList<>();
		List<MemberRewardTree> subTreeList = new ArrayList<MemberRewardTree>();
		MemberRewardTree subTree = null;
		for (Member mem : child) {
			// if (mem.getActive_days() != null &&
			// mem.getActive_days().isAfter(LocalDateTime.now())) {
			subTree = new MemberRewardTree();
			subTree.setId(mem.getId());
			subTree.setSponserId(mem.getReferedby());
			if (mem.getActive_days() != null && mem.getActive_days().isAfter(LocalDateTime.now())) {
				subTree.setStatus("ACTIVE");
			} else {
				subTree.setStatus("INACTIVE");
			}
			recursionTree(subTree, mem.getReferencecode(), mem.getId());
			subTreeList.add(subTree);
			// }
		}
		memberRewardTree.setChildren(subTreeList);
		return c;
	}
	
	private boolean sendSMS() throws UnsupportedEncodingException {
		List<Notification> list = notificationRepository.findByDeliveryStatusAndType("N", "SMS");
		for (Notification notification : list) {

			String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=" + sApiKey 
					+ "&ClientId=" + sClientId
					+ "&SenderId=" + sSenderId 
					+ "&Message=" + URLEncoder.encode(notification.getMessage(), StandardCharsets.UTF_8.toString())
					+ "&MobileNumbers=+91"+ notification.getMember().getPhonenumber() 
					+ "&Is_Unicode=" + is_Unicode 
					+ "&Is_Flash=" + is_Flash;
			
			URI uri = URI.create(url);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<?> reqentity = new HttpEntity<Object>(headers);

			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, reqentity, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				notificationRepository.deleteById(notification.getId());
			}
		}
		return true;
	}
}
