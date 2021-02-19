package com.ss.utils;

import java.util.Map;

import com.ss.app.entity.RewardTransaction;
import com.ss.app.model.RewardTransactionRepository;
import com.ss.app.vo.MemberRewardTree;
import com.ss.app.vo.MemberStat;

public class MemberLevel {

	static String awardMember = null;
	static Double awdVal = 0.0;
	static int activeCnt = 0;

	public static Double process(MemberRewardTree e, Map<String, Double> configMap,
			RewardTransactionRepository rewardTransactionRepository, int activeDirectCnt) {
		awdVal = 0.0;
		try {
			awardMember = e.getId();
			activeCnt = activeDirectCnt;
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

	public static Map<Integer, MemberStat> prepareLevelAndCount(MemberRewardTree e, Map<Integer, MemberStat> memberStat) {

		MemberStat stat = memberStat.get(e.getLevel());
		if (stat != null) {
			stat.setTotalCount(stat.getTotalCount() + 1);
			if ("ACTIVE".equals(e.getStatus())) {
				stat.setActiveCount(stat.getActiveCount() + 1);
			} else {
				stat.setInActiveCount(stat.getInActiveCount() + 1);
			}
		} else {
			MemberStat s = new MemberStat();
			s.setTotalCount(1L);
			if ("ACTIVE".equals(e.getStatus())) {
				s.setActiveCount(1L);
			} else {
				s.setInActiveCount(1L);
			}
			memberStat.put(e.getLevel(), s);
		}
		if (e.getChildren() != null && e.getChildren().size() > 0) {
			for (MemberRewardTree emp : e.getChildren()) {
				prepareLevelAndCount(emp, memberStat);
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
			int level = e.getLevel();
			rewardVal = getRewardValue(configMap, rewardVal, level);
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

	private static Double getRewardValue(Map<String, Double> configMap, Double rewardVal, int level) {
		if (level > 0) {
			if (activeCnt >= level && level <= 7) {
				rewardVal = configMap.get("L" + level);
			} else {
				if (activeCnt == 4 && level == 5) {
					rewardVal = configMap.get("L" + level);
				} else if (activeCnt == 5 && (level == 6 || level == 7)) {
					rewardVal = configMap.get("L" + level);
				} else {
					rewardVal = 0.0;
				}
			}
		}
		return rewardVal;
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
