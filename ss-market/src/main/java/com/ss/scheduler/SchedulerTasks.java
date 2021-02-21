package com.ss.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Scheduled(cron = "*/2 * * * * *")
	@Async
	private void smsTrigger() {
		try {
			//System.out.println("SMS TRIGGER");
			String sApiKey = "7dUGepGKDjLMq7Lagvs+rKgmztfdB/PbstEPi/oe/nk=";
			String sClientId = "dfa59504-961e-4710-a823-8d3556e57d5c";
			String sSenderId = "SSMRKT";
			boolean is_Unicode = false;
			boolean is_Flash = false;
			List<Notification> list = notificationRepository.findByDeliveryStatusAndType("N", "SMS");
			for (Notification notification : list) {

				String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=" + sApiKey + "&ClientId=" + sClientId
						+ "&SenderId=" + sSenderId + "&Message=" + notification.getMessage() + "&MobileNumbers=+91"
						+ notification.getMember().getPhonenumber() + "&Is_Unicode=" + is_Unicode + "&Is_Flash="
						+ is_Flash;

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

				HttpEntity<?> reqentity = new HttpEntity<Object>(headers);

				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, reqentity, String.class);
				if(response.getStatusCode().equals(HttpStatus.OK)) {
					notificationRepository.deleteById(notification.getId());
				}
			}
			//System.out.println("SMS END");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0 0 * * ?")
	private void dailyAward() {
		System.out.println("Start Daily Reward!");
		List<SSConfiguration> levels = ssConfigRepository.getRewardLevels();
		Map<String, Double> map = new HashMap<>();

		for (SSConfiguration ssConfiguration : levels) {
			map.put(ssConfiguration.getCode(), ssConfiguration.getValue());
		}
		List<Member> memberList = userRepository.getActiveMembersOnly();

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

		System.out.println("End Daily Reward!");
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
}
