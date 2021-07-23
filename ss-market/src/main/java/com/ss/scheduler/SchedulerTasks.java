package com.ss.scheduler;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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
import com.ss.app.model.NotificationRepository;
import com.ss.app.model.UserRepository;

@Component
public class SchedulerTasks {

	@Autowired
	private UserRepository userRepository;

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
		//dailyReward(memberList);
		//activeReward(memberList);
		System.out.println("End Daily Reward!");
	}

	
	private boolean sendSMS() throws UnsupportedEncodingException {
		List<Notification> list = notificationRepository.findByDeliveryStatusAndType("N", "SMS");
		for (Notification notification : list) {

			String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=" + sApiKey 
					+ "&ClientId=" + sClientId
					+ "&SenderId=" + sSenderId 
					+ "&Message=" + URLEncoder.encode(notification.getMessage(), StandardCharsets.UTF_8.toString())
					+ "&MobileNumbers=+91"+ notification.getMember() 
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
