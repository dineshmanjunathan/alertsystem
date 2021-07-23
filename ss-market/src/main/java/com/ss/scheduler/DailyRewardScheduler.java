package com.ss.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.app.model.UserRepository;

public class DailyRewardScheduler {
	
	@Autowired
	private UserRepository userRepository;

	//@Scheduled(cron = "0 0/2 * * * ?")
	//@Scheduled(fixedRate=5000)
	private void dailyAward() {
		System.out.println("Start Daily Reward!");
		//prepareReward();
		System.out.println("End Daily Reward!");
	}
	
//	private void prepareReward() {
//		List<Member> memberList = userRepository.getActiveMembers();
//		for(Member member : memberList) {
//			MemberRewardTree tree = new MemberRewardTree();
//			recursionTree(tree, member.getReferencecode(), member.getId());
//			System.out.println(tree.toString());
//		}
//	}
//	
//	private List<String> recursionTree(MemberRewardTree tree, String basekeyCode, String memberId) {
//		List<Member> child = userRepository.findByReferedby(basekeyCode);
//		List<String> c = new ArrayList<>();
//		List<MemberRewardTree> subTreeList = new ArrayList<>();
//		MemberRewardTree subTree = null;
//		for (Member mem : child) {
//			subTree = new MemberRewardTree();
//			subTree.setId(mem.getId());
//			subTree.setParent(memberId);
//			subTree.setText(mem.getId());
//			recursionTree(subTree, mem.getReferencecode(), mem.getId());
//			subTreeList.add(subTree);
//		}
//		tree.setChild(subTreeList);
//		return c;
//	}
}
