package com.ss.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Lists;
import com.ss.app.entity.Member;

public class Utils {
	/**
	 * 
	 * @param memberList
	 * @return
	 */
	public HashMap<String, Long> computeMemberCount(Iterable<Member> memberList) {
		HashMap<String, Long> userList = new HashMap<String, Long>();
		long active = 0;
		long inactive = 0;
		long stock = 0;
		long todaycount = 0;
		try {
			if (memberList != null) {
				ArrayList<Member> importOrderList = Lists.newArrayList(memberList);
				for (Member member : importOrderList) {
					LocalDateTime computeDate = member.getActive_days();

					if (member.getRole() != null && member.getRole().equalsIgnoreCase("ADMIN")) {
						continue;
					}

					if (computeDate == null) {
						computeDate = LocalDateTime.now();
					}
					if (computeDate.isAfter(LocalDateTime.now())) {
						++active;
					} else {
						++inactive;
					}
					if (member.getRole() != null && member.getRole().equalsIgnoreCase("STOCK_POINT"))
						++stock;
					if (member.getCreateon() != null && member.getCreateon().toLocalDate().equals(LocalDate.now()))
						++todaycount;
				}
			}
			userList.put("ACTIVE_MEMBER", active);
			userList.put("INACTIVE_MEMBER", inactive);
			userList.put("STOCK_MEMBER", stock);
			userList.put("TOTAL_MEMBER", active + inactive);
			userList.put("TOTAY_MEMBER", todaycount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static String checkNull(String str, String _default) {
		String result = null;
		try {
			if (str != null && str.trim().length() > 0 && !str.trim().equals("null")) {
				result = str.trim();
			} else {
				result = _default;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Long getOrderNumber() {
		Random random = new Random();
		int randomWithNextInt = random.nextInt(999999999);
		return (long) Math.abs(randomWithNextInt);
	}

	public static Map<String, String> getSSConfigTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1111", "GST INCENTIVE");
		map.put("1112", "COMPANY INCENTIVE");
		map.put("PR", "PURCHASE REWARD");
		map.put("L1", "LEVEL 1 REWARD");
		map.put("L2", "LEVEL 2 REWARD");
		map.put("L3", "LEVEL 3 REWARD");
		map.put("L4", "LEVEL 4 REWARD");
		map.put("L5", "LEVEL 5 REWARD");
		map.put("L6", "LEVEL 6 REWARD");

		return map;
	}

	/*
	 * public static ArrayList<SSConfiguration> getSSConfigTypeList() {
	 * 
	 * Map<String, String> map = getSSConfigTypeMap(); ArrayList<SSConfiguration>
	 * list = new ArrayList<SSConfiguration>(); for (String key : map.keySet()) {
	 * SSConfiguration ssConfiguration = new SSConfiguration();
	 * ssConfiguration.setCode(key); ssConfiguration.setDescription(map.get(key));
	 * list.add(ssConfiguration); }
	 * 
	 * return list; }
	 */

	public static void main(String[] arg) {

		Long rp = 40L;
		Long remaningPoint = 540L;
		Double config1 = Double.parseDouble(Utils.checkNull("10", "0.0"));
		Double config2 = Double.parseDouble(Utils.checkNull("5", "0.0"));
		Double deductAmt1 = (rp.doubleValue() / 100) * config1;
		Double deductAmt2 = (rp.doubleValue() / 100) * config2;
		Long totaldeduct = (long) (deductAmt1 + deductAmt2);

		remaningPoint = remaningPoint - rp;

		System.out.println("config1 -->" + config1);
		System.out.println("config2 -->" + config2);
		System.out.println("deductAmt1 -->" + deductAmt1);
		System.out.println("deductAmt2 -->" + deductAmt2);
		System.out.println("deductAmt2 -->" + deductAmt2);

	}
}
