package com.ss.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.common.collect.Lists;
import com.ss.app.entity.Member;
import com.ss.app.entity.SSConfiguration;

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
		int firstRandomVal = random.nextInt(9999);
		int secRandomVal = random.nextInt(9999);
		String val = String.valueOf(firstRandomVal) + String.valueOf(secRandomVal);
		return Long.parseLong(val);
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

	public static ArrayList<SSConfiguration> getSSConfigTypeList() {

		Map<String, String> map = getSSConfigTypeMap();
		ArrayList<SSConfiguration> list = new ArrayList<SSConfiguration>();
		for (String key : map.keySet()) {
			SSConfiguration ssConfiguration = new SSConfiguration();
			ssConfiguration.setCode(key);
			ssConfiguration.setDescription(map.get(key));
			list.add(ssConfiguration);
		}

		return list;
	}
	
	public static void sendSMS(String Message,String MobileNumbers) {

		try {
			String ApiKey = "";
			String ClientId = "";
			String SenderId = "";
			boolean Is_Unicode = true;
			boolean Is_Flash = true;

			URL url = new URL(
					"https://api.mylogin.co.in/api/v2/SendSMS?ApiKey="+ApiKey+"&ClientId="+ClientId+"&SenderId="+SenderId+"&Message="+Message+"&MobileNumbers="+MobileNumbers+"&Is_Unicode="+Is_Unicode+"&Is_Flash="+Is_Flash);// your
																																														// from
																																																					// .
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
	}
	
	

	public static void main(String[] arg) {
		System.out.println(" LocalDateTime.now().plusDays(2)->"+ LocalDate.now().plusDays(2));
		System.out.println(ChronoUnit.DAYS.between( LocalDate.now().plusDays(2).now(), LocalDate.now().plusDays(2)));
	}
}
