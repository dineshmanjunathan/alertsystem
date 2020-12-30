package com.ss.app.controller;


import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.app.dao.UserDao;
import com.ss.app.entity.CountryCode;
import com.ss.app.entity.Member;
import com.ss.app.model.CountryCodeRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.CountryCodeVo;
import com.ss.app.vo.MemberVo;
import com.ss.utils.ReportGenerator;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class MemberController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CountryCodeRepository countryCodeRepository;
		
	@RequestMapping("/")
	public String login(HttpServletRequest request,ModelMap model) {
		return "login";
	} 
	@RequestMapping("/login")
	public String inlogin(HttpServletRequest request,ModelMap model) {
		return "login";
	} 

	@RequestMapping("/menu")
	public String menu(HttpServletRequest request,ModelMap model) {
		return "menu";
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request,ModelMap model) {
		MemberVo ab = (MemberVo) request.getSession().getAttribute("USER");
		model.addAttribute("CURRENT_USER", ab);
		return "home";
	} 
	@RequestMapping("/register")
	public String user(HttpServletRequest request,ModelMap model) {
		Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
		model.addAttribute("countryCodeList", countryCodeList);
		return "user";
	} 
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,ModelMap model) {
		if(request.getSession()!=null) {
			request.getSession().invalidate();
			model.addAttribute("adminlogout", "Successfully logged out");
		}
		return "login";
	} 

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request,MemberVo user,ModelMap model) {
		try {
			
			request.getSession().setAttribute("LOGGED_ON", "true");
			request.getSession().setAttribute("MEMBER_ID", user.getId());
			return "menu";
			/*User ab = userDao.findUser(user);  
			if(ab != null) { 
				request.getSession().setAttribute("USER", ab);
				model.addAttribute("CURRENT_USER", ab);
				request.getSession().setAttribute("USER_NAME", ab.getName());
				return "home";   //menu 
			} else {
				model.addAttribute("errormsg","Username or Password is incorrect");
				return "login";
			}  */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping(value="/userlisting",method=RequestMethod.GET)
	public String adminListingSubmit(HttpServletRequest request,ModelMap model) {
		try {
			Iterable<Member> userList = userRepository.findAll(); 
			model.addAttribute("userList", userList); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userListing";
	}
	
	@RequestMapping(value="/member/tree",method=RequestMethod.GET)
	public String memberTree(HttpServletRequest request,ModelMap model) {
		try {
			/*
			 * Iterable<UserMaster> userList = userRepository.findAll();
			 * model.addAttribute("userList", userList);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "memberTree";
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerSubmit(HttpServletRequest request,MemberVo user,ModelMap model) {
		try {
			Member userEntity=new Member();
			BeanUtils.copyProperties(userEntity, user);
			userRepository.save(userEntity);
			System.out.println("Name ::"+user.getName());
			
			model.addAttribute("registersuccess", "Member"+user.getId()+ "Registered Successfully ");
			
			//model.addAttribute("userList", userList); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping(value="/user/edit",method=RequestMethod.GET)
	public String edit(@RequestParam("user_id")String userId,HttpServletRequest request,ModelMap model) { 
		try {
			Member user = userRepository.findById(userId).get();
			MemberVo MemberVo=new MemberVo();
			BeanUtils.copyProperties(MemberVo, user);
			model.addAttribute("user", MemberVo); 
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user";  
	}
	

	@RequestMapping(value="/user/edit",method=RequestMethod.POST)
	public String editSubmit(HttpServletRequest request,MemberVo user,ModelMap model) {
		Member userEntity=new Member();
		try {
			BeanUtils.copyProperties(userEntity, user);
			System.out.println(userEntity.getId());
			userRepository.save(userEntity);
			Iterable<Member> userList = userRepository.findAll();  
			model.addAttribute("userList", userList); 
			model.addAttribute("successMessage","Successfully Edited Admin Record"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userListing";
	}	

	@RequestMapping("/user/delete")
	public String delete(@RequestParam("user_id")String userId,HttpServletRequest request,ModelMap model) { 
		try {
			userRepository.deleteById(userId);
			//userRepository.delete(user);
			model.addAttribute("deletesuccessmessage","Deleted Successfully"); 
			Iterable<Member> userList = userRepository.findAll();
			model.addAttribute("userList", userList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "userListing";
	}

	@RequestMapping(value="/user/delete",method=RequestMethod.POST)
	public String deleteSubmit(HttpServletRequest request,ModelMap model) { 
		try {
			String user_id = request.getParameter("id");
			//Optional<User> user=userRepository.findById(Long.parseLong(user_id));
			userRepository.deleteById(user_id);
			model.addAttribute("deletesuccessmessage","Deleted Successfully"); 
			List<MemberVo> userList;
			userList = userDao.findUsers();
			model.addAttribute("userList", userList); 
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return "userListing";
	}
	
	@RequestMapping("/countryCodeListing")
	public String countryCodeListing(HttpServletRequest request,ModelMap model) { 
		try {
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "countryCodeListing";
	}

	@RequestMapping(value="/countryCode/delete",method=RequestMethod.GET)
	public String countryCodeDelete(@RequestParam("id")String id,HttpServletRequest request,ModelMap model) { 
		try {
			countryCodeRepository.deleteById(Long.parseLong(id));
			model.addAttribute("deletesuccessmessage","Deleted Successfully"); 
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "countryCodeListing";
	}
	
	@RequestMapping(value="/countryCode/edit",method=RequestMethod.GET)
	public String countryCodeEdit(@RequestParam("id")String id,HttpServletRequest request,ModelMap model) { 
		try {
			CountryCode countryCode = countryCodeRepository.findById(Long.parseLong(id)).get();
			CountryCodeVo countryCodeVo=new CountryCodeVo();
			BeanUtils.copyProperties(countryCodeVo, countryCode);
			model.addAttribute("countryCode", countryCodeVo); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCode";  
	}
	

	@RequestMapping(value="/countryCode/edit",method=RequestMethod.POST)
	public String countryCodeEditSubmit(HttpServletRequest request,CountryCodeVo countryCodeVo,ModelMap model) {
		CountryCode countryCode=new CountryCode();
		try {
			BeanUtils.copyProperties(countryCode, countryCodeVo);
			countryCodeRepository.save(countryCode);
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList); 
			model.addAttribute("successMessage","Successfully Edited Admin Record"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCodeListing";
	}	
	
	@RequestMapping(value="/countryCode/save",method=RequestMethod.POST)
	public String countryCodeSubmit(HttpServletRequest request,CountryCodeVo countryCodeVo,ModelMap model) {
		try {
			CountryCode countryCode=new CountryCode();
			BeanUtils.copyProperties(countryCode, countryCodeVo);
			countryCodeRepository.save(countryCode);
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCodeListing";
	}

	

	@RequestMapping(value = "/user/generate/pdf", method = RequestMethod.GET)
	public void export(@RequestParam("user_id")String userId,ModelAndView model, HttpServletResponse response){
		try {
			JasperPrint jasperPrint = null;
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", String.format("attachment; filename=" + userId +".pdf" ));
			Member user = userRepository.findById(userId).get();
			OutputStream out = response.getOutputStream();
			ReportGenerator reportGenerator = new ReportGenerator();
			jasperPrint = reportGenerator.getJasperContext(reportGenerator.getReportData(user),"templates/reg.jrxml");
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/wallet/balance")
	public ResponseEntity<Integer> loadWallet(@RequestParam("memberId") String memberId,HttpServletRequest request,ModelMap model) { 
		Integer balance = userRepository.getWalletBalance(memberId);
		model.addAttribute("balance", balance);  
		return new ResponseEntity(balance, HttpStatus.OK);
	}
	
	
} 