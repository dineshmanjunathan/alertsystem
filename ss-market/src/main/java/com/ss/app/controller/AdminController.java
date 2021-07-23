package com.ss.app.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.app.entity.EnterMessage;
import com.ss.app.entity.Member;
import com.ss.app.model.CategoryRepository;
import com.ss.app.model.NotificationRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.EnterMessageVo;
import com.ss.app.vo.MemberVo;
import com.ss.utils.Utils;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@RequestMapping("/admin/login")
	public String inlogin(HttpServletRequest request, ModelMap model) {
		model.addAttribute("ROLE", "ADMIN");
		if (request.getSession() != null && request.getSession().getAttribute("MEMBER_ID") != null) {
			request.getSession().invalidate();
		}
		return "commonLogin";
	}

	@RequestMapping("/admin")
	public String adminLogin(HttpServletRequest request, ModelMap model) {
		model.addAttribute("ROLE", "ADMIN");
		if (request.getSession() != null && request.getSession().getAttribute("MEMBER_ID") != null) {
			request.getSession().invalidate();
		}
		return "commonLogin";
	}

	@RequestMapping("/admin/member/listing")
	public String adminListing(HttpServletRequest request, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		Iterable<Member> memberList = userRepository.findAll();
		model.addAttribute("memberList", memberList);
		return "memberListing";
	}


	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
	public String edit(HttpServletRequest request, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		return "useredit";
	}

	@RequestMapping("/admin/user/delete")
	public String delete(@RequestParam("user_id") String userId, HttpServletRequest request, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		try {

			//addressRepository.deleteByMember_Id(userId);
			userRepository.deleteById(userId);
			notificationRepository.deleteByMember_Id(userId);

			model.addAttribute("deletesuccessmessage", "Member Deleted Successfully.");
			Iterable<Member> memberList = userRepository.findAll();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "memberListing";
	}

	@RequestMapping("/admin/categoryCodeListing")
	public String categoryCodeListing(HttpServletRequest request, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		try {
			List<EnterMessage> categoryCodeList = (List<EnterMessage>) categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

	

	/*
	 * @RequestMapping(value = "/admin/categoryCode/edit", method =
	 * RequestMethod.GET) public String categoryCodeEdit(@RequestParam("id") String
	 * id, HttpServletRequest request, ModelMap model) {
	 * if(Utils.validateAdmin(request)) { return "commonLogin"; } try { EnterMessage
	 * categoryCode = categoryRepository.findByCode(id); EnterMessageVo
	 * categoryCodeVo = new EnterMessageVo(); BeanUtils.copyProperties(categoryCode,
	 * categoryCodeVo); model.addAttribute("categoryCode", categoryCodeVo); } catch
	 * (Exception e) { e.printStackTrace(); } return "categoryCode"; }
	 */

	@RequestMapping(value = "/admin/categoryCode/edit", method = RequestMethod.POST)
	public String categoryCodeEditSubmit(HttpServletRequest request, EnterMessageVo categoryCodeVo, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		EnterMessage categoryCode = new EnterMessage();
		try {
			BeanUtils.copyProperties(categoryCodeVo, categoryCode);
			categoryRepository.save(categoryCode);
			Iterable<EnterMessage> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList);
			model.addAttribute("successMessage", "Category Updated Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

	@RequestMapping("/admin/categoryCode")
	public String incategoryCode(HttpServletRequest request, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		return "categoryCode";
	}

	@RequestMapping(value = "/admin/categoryCode/save", method = RequestMethod.POST)
	public String categoryCodeSubmit(HttpServletRequest request, EnterMessageVo categoryCodeVo, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		try {
			EnterMessage categoryCode = new EnterMessage();
			BeanUtils.copyProperties(categoryCodeVo, categoryCode);
			categoryRepository.save(categoryCode);
			Iterable<EnterMessage> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList);
			model.addAttribute("successMessage", "Category Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

}
