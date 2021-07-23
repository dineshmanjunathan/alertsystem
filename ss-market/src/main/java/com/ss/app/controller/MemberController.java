package com.ss.app.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.app.entity.Member;
import com.ss.app.entity.Notification;
import com.ss.app.model.NotificationRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.MemberVo;
import com.ss.utils.Utils;

@Controller
public class MemberController {

	@Autowired
	private UserRepository userRepository;

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
	public String loginSubmit(HttpServletRequest request, MemberVo user, ModelMap model) {
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
			
			return "entermsg";
		} catch (Exception e) {
			model.addAttribute("errormsg", "Member does not Exists!");
		}
		return "login";
	}



	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSubmit(HttpServletRequest request, MemberVo user, ModelMap model) {
		try {
			String role = (String) request.getSession().getAttribute("ROLE");

			Member userEntity = new Member();
			model.addAttribute("member", user);

			BeanUtils.copyProperties(user, userEntity, "createon", "updatedon");

			Member phMember = userRepository.save(userEntity);

			phMember = userRepository.save(phMember);

			model.addAttribute("registersuccess", "Member Registered Successfully!");
			model.addAttribute("successMsgMemberId",
					"Your Login Member Id and Referral Code is <b>" + phMember.getId() + "</b>");
			model.addAttribute("successMsgNote", "<b>Note:</b> Please save above details for future reference.");

			// ADD NOTIFY
			String msg = "Dear " + phMember.getName() + ", Welcome to Xylo Alert System.\nYour User ID : "
					+ phMember.getId() + " Password : " + phMember.getPassword() + "\nPlease login to #";
			Notification notification = setNotificationSMS(msg, phMember);
			notificationRepository.save(notification);

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
			Member user = userRepository.findById(userId).get();
			model.addAttribute("member", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "useredit";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public String editSubmit(HttpServletRequest request, MemberVo user, ModelMap model) {
		if(Utils.validateMember(request)) {
			return "login";
		}
		Member userEntity = new Member();
		try {
			Member actualmember = userRepository.findById(user.getId()).get();
			BeanUtils.copyProperties(user, userEntity);
			
			userEntity.setUpdatedon(new Date(System.currentTimeMillis()));

			Member member = userRepository.save(userEntity);

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
		Member member = userRepository.findById(memberId).get();
		return new ResponseEntity<String>(member.getName(), HttpStatus.OK);
	}
	
	private Notification setNotificationSMS(String msg, Member member) {
		Notification notification = new Notification();
		if (msg != null && !msg.isEmpty() && member!=null) {
			notification.setType("SMS");
			notification.setMessage(msg);
			notification.setMember(member);
		}
		return notification;
	}
}