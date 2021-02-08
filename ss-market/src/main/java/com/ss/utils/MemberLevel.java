package com.ss.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.ss.app.entity.RewardTransaction;
import com.ss.app.model.RewardTransactionRepository;
import com.ss.app.vo.MemberRewardTree;
import com.ss.app.vo.MemberStat;

public class MemberLevel {

	static String awardMember = null;
	static Double awdVal = 0.0;

	public static Double process(String a, Map<String, Double> configMap,
			RewardTransactionRepository rewardTransactionRepository) {
		Gson g = new Gson();
		awdVal = 0.0;
		try {
			MemberRewardTree e = g.fromJson(a, MemberRewardTree.class);
			awardMember = e.getId();
			prepareMember(e, -1);
			rewardMember(e, configMap, rewardTransactionRepository);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return awdVal;

	}

	public static void prepareMember(MemberRewardTree e, int level) {
		setParentAndLevel(e, level, e.getId());
	}

	private static void setParentAndLevel(MemberRewardTree e, int lvl, String parent) {
		try {
			e.setLevel(lvl);
			if (e.getChildren() != null && e.getChildren().size() > 0) {
				lvl++;
				for (MemberRewardTree emp : e.getChildren()) {
					setParentAndLevel(emp, lvl, e.getId());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	static Map<Integer, MemberStat> memberStat = new HashMap<Integer, MemberStat>();
	public static Map<Integer, MemberStat> prepareLevelAndCount(MemberRewardTree e) {
		
		MemberStat stat = memberStat.get(e.getLevel());
		if(stat != null) {
			stat.setTotalCount(stat.getTotalCount()+1);
			if("ACTIVE".equals(e.getStatus())) {
				stat.setActiveCount(stat.getActiveCount()+1);
			} else {
				stat.setInActiveCount(stat.getInActiveCount()+1);
			}
		} else {
			MemberStat s = new MemberStat();
			s.setTotalCount(1L);
			if("ACTIVE".equals(e.getStatus())) {
				s.setActiveCount(1L);
			} else {
				s.setInActiveCount(1L);
			}
			memberStat.put(e.getLevel(), s);
		}
		if (e.getChildren() != null && e.getChildren().size() > 0) {
			for (MemberRewardTree emp : e.getChildren()) {
				prepareLevelAndCount(emp);
			}
		} else {
			return memberStat;
		}
		return memberStat;
	}
	

	public static void rewardMember(MemberRewardTree e, Map<String, Double> configMap,
			RewardTransactionRepository rewardTransactionRepository) {
		try {
			System.out.println(e);
			Double rewardVal = 0.0;
			if (e.getLevel() > 0) {
				rewardVal = configMap.get("L" + e.getLevel());
			}
			if (rewardVal != null && rewardVal > 0) {
				RewardTransaction reward = prepareRewarTransaction(e, rewardVal);
				awdVal = awdVal + rewardVal;
				rewardTransactionRepository.save(reward);
			}
			if (e.getChildren() != null && e.getChildren().size() > 0) {
				for (MemberRewardTree emp : e.getChildren()) {
					rewardMember(emp, configMap, rewardTransactionRepository);
				}
			} else {
				return;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private static RewardTransaction prepareRewarTransaction(MemberRewardTree e, Double rewardVal) {
		RewardTransaction reward = new RewardTransaction();
		reward.setMemberid(e.getId());
		reward.setPoint(rewardVal);
		reward.setOrderNumber(100001L);
		reward.setRewardedMember(awardMember);
		reward.setSponserId(e.getSponserId());
		return reward;
	}
	
	
}
