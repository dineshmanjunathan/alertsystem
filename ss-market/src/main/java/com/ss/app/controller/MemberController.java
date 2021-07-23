package com.ss.app.controller;

import java.io.OutputStream;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.ss.app.entity.Member;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.MemberVo;
import com.ss.utils.ReportGenerator;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class MemberController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String login(HttpServletRequest request, ModelMap model) {
		return "login";
	}

	@RequestMapping("/login")
	public String inlogin(HttpServletRequest request, ModelMap model) {
		return "login";
	}

	@RequestMapping("/menu")
	public String menu(HttpServletRequest request, ModelMap model) {
		return "menu";
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request, ModelMap model) {
		MemberVo ab = (MemberVo) request.getSession().getAttribute("USER");
		model.addAttribute("CURRENT_USER", ab);
		return "home";
	}

	@RequestMapping("/register")
	public String user(HttpServletRequest request, ModelMap model) {

		return "user";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, ModelMap model) {
		if (request.getSession() != null) {
			request.getSession().invalidate();
			model.addAttribute("adminlogout", "Successfully logged out");
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, MemberVo user, ModelMap model) {
		try {
//			Member member = userRepository.findByIdAndPasswordAndRole(user.getId(), user.getPassword(), "MEMBER").get();
//			if (member != null && member.getId()!=null) {
//				if (!user.getPassword().equals(member.getPassword())) {
//					model.addAttribute("errormsg", "Password is incorrect!");
//					return "login";
//				}
//				request.getSession().setAttribute("LOGGED_ON", "true");
//				request.getSession().setAttribute("MEMBER_ID", user.getId());
//				request.getSession().setAttribute("MEMBER_NAME", member.getName());
//				request.getSession().setAttribute("ROLE", member.getRole());
//				if(member.getActive_days() !=null) {
//					long numOfDays = ChronoUnit.DAYS.between( LocalDateTime.now(), member.getActive_days())+1;
//					request.getSession().setAttribute("ACTIVE_DAYS", numOfDays);
//				} else {
//					request.getSession().setAttribute("ACTIVE_DAYS", 0);
//				}
//				return "menu";
//			} else {
//				model.addAttribute("errormsg", "User Id or Password is incorrect!");
//			}
			
			return "menu";
		} catch (Exception e) {
			model.addAttribute("errormsg", "Member does not Exists!");
		}
		return "login";
	}

	@RequestMapping(value = "/wallet", method = RequestMethod.GET)
	public String getWalletBalance(HttpServletRequest request, ModelMap model) {
		try {
			String userId = (String) request.getSession().getAttribute("MEMBER_ID");

			Member member = userRepository.findById(userId).get();
			if (member != null && member.getId() != null) {

				member.setTotalbalance(member.getWalletBalance() + member.getWalletWithdrawn());
				model.addAttribute("userwallet", member);
				return "wallet";
			} else {
				model.addAttribute("errormsg", "Try again sometime");
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping(value = "/wallet/rePurchase", method = RequestMethod.POST)
	public String redirectToRePurcahse(HttpServletRequest request, MemberVo user, ModelMap model) {
		Member userEntity = new Member();
		try {

			BeanUtils.copyProperties(user, userEntity);
			model.addAttribute("member", userEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rePurchase";
	}
	


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSubmit(HttpServletRequest request, MemberVo user, ModelMap model) {
		try {
			String role = (String) request.getSession().getAttribute("ROLE");

			Member userEntity = new Member();
			model.addAttribute("member", user);
			
			BeanUtils.copyProperties(user, userEntity, "createon", "updatedon");
			if (StringUtils.isNotEmpty(user.getReferedby())) {
				String referedBy = userRepository.checkSponserExists(user.getReferedby());
				if (StringUtils.isEmpty(referedBy)) {
					
					model.addAttribute("errormsg", "Invalid Sponser Id.");
					if(role!=null && role.equals("ADMIN")) {
						model.addAttribute("member", userEntity);
						return "useredit";
					}else {
						return "user";
					}
				}
			}
			userRepository.save(userEntity);
			
			if(role!=null && role.equals("ADMIN")) {
				model.addAttribute("successMessage", "Member Created Successfully! ");
				Iterable<Member> memberList = userRepository.findAll();
				model.addAttribute("memberList",memberList);
				return "memberListing";
			}else {
				model.addAttribute("registersuccess", "Member Registered Successfully! ");
			}
			
			//TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Member Registered Failed! ");
			return "user";
		}
		return "login";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("user_id") String userId,HttpServletRequest request, ModelMap model) {
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
		Member userEntity = new Member();
		try {
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

	

	@RequestMapping(value = "/user/generate/pdf", method = RequestMethod.GET)
	public void export(@RequestParam("user_id") String userId, ModelAndView model, HttpServletResponse response) {
		try {
			JasperPrint jasperPrint = null;
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", String.format("attachment; filename=" + userId + ".pdf"));
			Member user = userRepository.findById(userId).get();
			OutputStream out = response.getOutputStream();
			ReportGenerator reportGenerator = new ReportGenerator();
			jasperPrint = reportGenerator.getJasperContext(reportGenerator.getReportData(user), "templates/reg.jrxml");
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/wallet/balance")
	public ResponseEntity<Member> loadWallet(@RequestParam("memberId") String memberId, HttpServletRequest request,
			ModelMap model) {
		Member member = userRepository.findById(memberId).get();
		model.addAttribute("balance", member.getWalletBalance());
		model.addAttribute("withdrawn", member.getWalletWithdrawn());
		model.addAttribute("totalEarned", member.getWalletBalance() + member.getWalletWithdrawn());
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@RequestMapping("/contactus")
	public String contactus(HttpServletRequest request, ModelMap model) {
		return "contactus";
	}
	
	@RequestMapping("/get/member")
	public ResponseEntity<String> findMember(@RequestParam("memberId") String memberId, HttpServletRequest request,ModelMap model) {
		Member member = userRepository.findById(memberId).get();
		return new ResponseEntity<String>(member.getName(), HttpStatus.OK);
	}
	
}