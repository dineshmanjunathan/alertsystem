package com.alert.app.controller;

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

import com.alert.app.entity.Message;
import com.alert.app.entity.User;
import com.alert.app.model.MessageRepository;
import com.alert.app.model.NotificationRepository;
import com.alert.app.model.UserRepository;
import com.alert.app.vo.MessageVo;
import com.alert.app.vo.UserVo;
import com.alert.utils.Utils;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository categoryRepository;

	
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
		Iterable<User> memberList = userRepository.findAll();
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

	

	@RequestMapping("/admin/categoryCodeListing")
	public String categoryCodeListing(HttpServletRequest request, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		try {
			List<Message> categoryCodeList = (List<Message>) categoryRepository.findAll();
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
	public String categoryCodeEditSubmit(HttpServletRequest request, MessageVo categoryCodeVo, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		Message categoryCode = new Message();
		try {
			BeanUtils.copyProperties(categoryCodeVo, categoryCode);
			categoryRepository.save(categoryCode);
			Iterable<Message> categoryCodeList = categoryRepository.findAll();
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
	public String categoryCodeSubmit(HttpServletRequest request, MessageVo categoryCodeVo, ModelMap model) {
		if(Utils.validateAdmin(request)) {
			return "commonLogin";
		}
		try {
			Message categoryCode = new Message();
			BeanUtils.copyProperties(categoryCodeVo, categoryCode);
			categoryRepository.save(categoryCode);
			Iterable<Message> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList);
			model.addAttribute("successMessage", "Category Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

}
