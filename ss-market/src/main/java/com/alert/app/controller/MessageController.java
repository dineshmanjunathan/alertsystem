package com.alert.app.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alert.app.entity.Message;
import com.alert.app.entity.Notification;
import com.alert.app.entity.User;
import com.alert.app.model.MessageRepository;
import com.alert.app.model.NotificationRepository;
import com.alert.app.model.UserRepository;
import com.alert.app.vo.MessageVo;
import com.alert.app.vo.UserVo;
import com.alert.utils.Utils;

@Controller
public class MessageController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	

	@RequestMapping("/")
	public String landingPage(HttpServletRequest request, ModelMap model) {
		if (request.getSession() != null && request.getSession().getAttribute("MEMBER_ID") != null) {
			request.getSession().invalidate();
		}
		return "login";
	}

	@RequestMapping("/login")
	public String inlogin(HttpServletRequest request, ModelMap model) {
		if (request.getSession() != null && request.getSession().getAttribute("MEMBER_ID") != null) {
			request.getSession().invalidate();
		}
		return "login";
	}

	@RequestMapping("/menu")
	public String menu(HttpServletRequest request, ModelMap model) {
		if(Utils.validateMember(request)) {
			return "login";
		}
		return "menu";
	}


	@RequestMapping("/register")
	public String user(HttpServletRequest request, ModelMap model) {
		if (request.getSession() != null && request.getSession().getAttribute("MEMBER_ID") != null) {
			request.getSession().invalidate();
		}
		return "user";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, ModelMap model) {
		if(Utils.validateMember(request)) {
			return "login";
		}
		String redirectPath = "login";
		if (request.getSession() != null) {
			if (request.getSession().getAttribute("ROLE") != null
					&& request.getSession().getAttribute("ROLE").equals("ADMIN")) {
				model.addAttribute("ROLE", "ADMIN");
				redirectPath = "commonLogin";
			} else if (request.getSession().getAttribute("ROLE") != null
					&& request.getSession().getAttribute("ROLE").equals("STOCK_POINT")) {
				model.addAttribute("ROLE", "STOCK_POINT");
				redirectPath = "commonLogin";
			}

			request.getSession().invalidate();
			model.addAttribute("adminlogout", "Successfully logged out");
		}
		return redirectPath;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, UserVo user, ModelMap model) {
		try {
//			Member member = userRepository.findByIdAndPasswordAndRole(user.getId(), user.getPassword(), "MEMBER").get();
//			if (member != null && member.getId() != null) {
//				if (!user.getPassword().equals(member.getPassword())) {
//					model.addAttribute("errormsg", "Password is incorrect!");
//					return "login";
//				}
//				request.getSession().setAttribute("LOGGED_ON", "true");
//				request.getSession().setAttribute("MEMBER_ID", user.getId());
//				request.getSession().setAttribute("MEMBER_NAME", member.getName());
//				request.getSession().setAttribute("ROLE", member.getRole());
//				request.getSession().setAttribute("REFERENCE_CODE", member.getReferencecode());
//				if (member.getActive_days() != null) {
//					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
//					java.util.Date date = Date.from(member.getActive_days().atZone(ZoneId.systemDefault()).toInstant());
//					request.getSession().setAttribute("ACTIVE_DAYS", sdf.format(date));
//				} else {
//					LocalDateTime time = LocalDateTime.now();
//					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
//					java.util.Date date = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
//					request.getSession().setAttribute("ACTIVE_DAYS", sdf.format(date));
//				}
//				return "menu";
//			} else {
//				model.addAttribute("errormsg", "User Id or Password is incorrect!");
//			}
			
			return "message";
		} catch (Exception e) {
			model.addAttribute("errormsg", "Member does not Exists!");
		}
		return "login";
	}



	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSubmit(HttpServletRequest request, UserVo user, ModelMap model) {
		try {
			String role = (String) request.getSession().getAttribute("ROLE");

			User userEntity = new User();
			model.addAttribute("member", user);

			BeanUtils.copyProperties(user, userEntity, "createon", "updatedon");


			// TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Member Registered Failed! please try again.");
			return "user";
		}
		return "login";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("user_id") String userId, HttpServletRequest request, ModelMap model) {
		if(Utils.validateMember(request)) {
			return "login";
		}
		try {
			User user = userRepository.findById(userId).get();
			model.addAttribute("member", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "useredit";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public String editSubmit(HttpServletRequest request, UserVo user, ModelMap model) {
		if(Utils.validateMember(request)) {
			return "login";
		}
		User userEntity = new User();
		try {
			User actualmember = userRepository.findById(user.getId()).get();
			BeanUtils.copyProperties(user, userEntity);
			
			userEntity.setUpdatedon(new Date(System.currentTimeMillis()));

			User member = userRepository.save(userEntity);

			model.addAttribute("member", member);
			model.addAttribute("successMessage", "Member profile updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "useredit";
	}


	@RequestMapping("/get/member")
	public ResponseEntity<String> findMember(@RequestParam("memberId") String memberId, HttpServletRequest request,
			ModelMap model) {
		User member = userRepository.findById(memberId).get();
		return new ResponseEntity<String>(member.getName(), HttpStatus.OK);
	}
	
	@RequestMapping("message/trigger")
	public String triggerMsg(HttpServletRequest request, MessageVo message,
			ModelMap model,@RequestParam(required = false) MultipartFile file) {
		Notification notifyEntity = new Notification();
		try {

			if (file!=null && !file.isEmpty()) {
				byte[] fileByte = file.getBytes();
				notifyEntity.setFile(fileByte);
			}
			notifyEntity.setMobileno(message.getMobileno());
			notifyEntity.setMessage(message.getMessage());
			notifyEntity.setType(message.getType());
			notificationRepository.save(notifyEntity);
			
			// model.addAttribute("message", msgEntity);
			model.addAttribute("successMessage",
					"Your Request is taken.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message";
	}
	
	private Notification setNotificationSMS(String msg, User member) {
		Notification notification = new Notification();
		if (msg != null && !msg.isEmpty() && member!=null) {
			notification.setType("SMS");
			notification.setMessage(msg);
		}
		return notification;
	}
}