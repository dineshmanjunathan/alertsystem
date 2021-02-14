package com.ss.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ss.app.entity.Member;
import com.ss.app.entity.SSConfiguration;
import com.ss.app.model.RewardTransactionRepository;
import com.ss.app.model.SSConfigRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.MemberRewardTree;
import com.ss.utils.MemberLevel;

@Component
public class DailyRewardScheduler {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SSConfigRepository ssConfigRepository;
	@Autowired
	RewardTransactionRepository rewardTransactionRepository;

	// @Scheduled(fixedRate=5000)
	// @Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(cron = "0 0 0/1 * * ?")
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
			Double awdVal = MemberLevel.process(memberRewardTree, map, rewardTransactionRepository,getActiveDirectCount(member));
			if (awdVal > 0) {
				member.setWalletBalance(member.getWalletBalance() + awdVal.longValue());
				userRepository.save(member);
			}
		}

		System.out.println("End Daily Reward!");
	}

	private int getActiveDirectCount(Member member) {
		int activeDirectCnt=0;
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
		}
		memberRewardTree.setChildren(subTreeList);
		return c;
	}
}
