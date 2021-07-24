package com.alert.scheduler;

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

import com.alert.app.entity.Notification;
import com.alert.app.model.NotificationRepository;
import com.alert.app.model.UserRepository;
import com.alert.utils.Utils;

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

	private void sendSMS() throws Exception {
		Notification notification = notificationRepository.findFirstByDeliveryStatus("N");

		if (notification != null) {
			if (notification.getType().equalsIgnoreCase("T")) {

				byText(notification);
			} else if (notification.getType().equalsIgnoreCase("F")) {
				byFile(notification);
			}
		}

	}

	private void byText(Notification notification) throws Exception {

		List<String> mobileList = Utils.stringtolist(notification.getMobileno());
		int count = 0;

		for (String phoneNo : mobileList) {

			String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=" + sApiKey + "&ClientId=" + sClientId
					+ "&SenderId=" + sSenderId + "&Message="
					+ URLEncoder.encode(notification.getMessage(), StandardCharsets.UTF_8.toString())
					+ "&MobileNumbers=+91" + phoneNo + "&Is_Unicode=" + is_Unicode + "&Is_Flash=" + is_Flash;

			URI uri = URI.create(url);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<?> reqentity = new HttpEntity<Object>(headers);

			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, reqentity, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				count++;
			}
		}

		notificationRepository.updateDeliveryStatus(count, notification.getId());

	}

	private void byFile(Notification notification) throws Exception {

		List<String> mobileList = Utils.stringtolist(notification.getMobileno());
		int count = 0;

		for (String phoneNo : mobileList) {

			String url = "https://api.mylogin.co.in/api/v2/SendSMS?ApiKey=" + sApiKey + "&ClientId=" + sClientId
					+ "&SenderId=" + sSenderId + "&Message="
					+ URLEncoder.encode(notification.getMessage(), StandardCharsets.UTF_8.toString())
					+ "&MobileNumbers=+91" + phoneNo + "&Is_Unicode=" + is_Unicode + "&Is_Flash=" + is_Flash;

			URI uri = URI.create(url);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<?> reqentity = new HttpEntity<Object>(headers);

			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, reqentity, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				count++;
			}
		}

		notificationRepository.updateDeliveryStatus(count, notification.getId());

	}
}
