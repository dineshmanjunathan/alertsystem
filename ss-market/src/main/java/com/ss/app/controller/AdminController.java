package com.ss.app.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.app.entity.Category;
import com.ss.app.entity.Member;
import com.ss.app.model.CategoryRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.CategoryVo;
import com.ss.app.vo.MemberVo;
import com.ss.utils.Utils;

@Controller
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@RequestMapping("/admin/login")
	public String inlogin(HttpServletRequest request,ModelMap model) {
		model.addAttribute("ROLE","ADMIN");
		return "commonLogin";
	} 
	
	@RequestMapping("/admin")
	public String adminLogin(HttpServletRequest request,ModelMap model) {
		model.addAttribute("ROLE","ADMIN");
		return "commonLogin";
	} 
	
	@RequestMapping("/admin/menu")
	public String adminMenu(HttpServletRequest request,ModelMap model) {
		model.addAttribute("ROLE","ADMIN");
		
		Iterable<Member> memberList = userRepository.findAll();
		if (memberList != null) {
			Utils utils = new Utils();
			HashMap<String, Long> countMap = utils.computeMemberCount(memberList);
			model.mergeAttributes(countMap);
		}
		
		return "adminMenu";
	} 
	
	@RequestMapping("/admin/member/listing")
	public String adminListing(HttpServletRequest request,ModelMap model) {
		Iterable<Member> memberList = userRepository.findAll();
		model.addAttribute("memberList",memberList);
		return "memberListing";
	} 
	
	@RequestMapping(value="/admin/login",method=RequestMethod.POST)
	public String stockPointLoginSubmit(HttpServletRequest request,MemberVo user,ModelMap model) {
		try {
			Member member = userRepository.findByIdAndPasswordAndRole(user.getId(), user.getPassword(), "ADMIN").get();
			if(member!=null && member.getId() !=null) {
				request.getSession().setAttribute("LOGGED_ON", "true");
				request.getSession().setAttribute("MEMBER_ID", user.getId());
				request.getSession().setAttribute("MEMBER_NAME", member.getName());
				request.getSession().setAttribute("ROLE", member.getRole());
				
				Iterable<Member> memberList = userRepository.findAll();
				if (memberList != null) {
					Utils utils = new Utils();
					HashMap<String, Long> countMap = utils.computeMemberCount(memberList);
					model.mergeAttributes(countMap);
				}
				
				return "adminMenu";
			} else {
				model.addAttribute("errormsg","User Id or Password is incorrect!");
			}
		} catch (Exception e) {
			model.addAttribute("errormsg","Admin does not Exists!");
		}
		return "commonLogin";
	}
	
	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
	public String edit(HttpServletRequest request, ModelMap model) {
		return "useredit";
	}
	
	@RequestMapping("/admin/user/delete")
	public String delete(@RequestParam("user_id") String userId, HttpServletRequest request, ModelMap model) {
		try {
			userRepository.deleteById(userId);

			model.addAttribute("deletesuccessmessage", "Member Deleted Successfully.");
			Iterable<Member> memberList = userRepository.findAll();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "memberListing";
	}
	
	@RequestMapping("/admin/categoryCodeListing")
	public String categoryCodeListing(HttpServletRequest request,ModelMap model) { 
		try {
			Iterable<Category> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "categoryCodeListing";
	}

	@RequestMapping(value="/admin/categoryCode/delete",method=RequestMethod.GET)
	public String categoryCodeDelete(@RequestParam("id")String id,HttpServletRequest request,ModelMap model) { 
		try {
			try {
				categoryRepository.deleteByCode(id);
			}catch (DataIntegrityViolationException e) {
				Iterable<Category> categoryCodeList = categoryRepository.findAll();
				model.addAttribute("categoryCodeList", categoryCodeList); 
				model.addAttribute("errormessage","Product avaialbe for this category."); 
				return "categoryCodeListing";
			}
			model.addAttribute("deletesuccessmessage","Category Deleted Successfully."); 
			Iterable<Category> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "categoryCodeListing";
	}
	
	@RequestMapping(value="/admin/categoryCode/edit",method=RequestMethod.GET)
	public String categoryCodeEdit(@RequestParam("id")String id,HttpServletRequest request,ModelMap model) { 
		try {
			Category categoryCode = categoryRepository.findByCode(id);
			CategoryVo categoryCodeVo=new CategoryVo();
			BeanUtils.copyProperties(categoryCode,categoryCodeVo);
			model.addAttribute("categoryCode", categoryCodeVo); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCode";  
	}


	@RequestMapping(value="/admin/categoryCode/edit",method=RequestMethod.POST)
	public String categoryCodeEditSubmit(HttpServletRequest request,CategoryVo categoryCodeVo,ModelMap model) {
		Category categoryCode=new Category();
		try {
			BeanUtils.copyProperties(categoryCodeVo,categoryCode);
			categoryRepository.save(categoryCode);
			Iterable<Category> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList); 
			model.addAttribute("successMessage","Category Updated Successfully."); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}	
	
	@RequestMapping("/admin/categoryCode")
	public String incategoryCode(HttpServletRequest request,ModelMap model) {
		return "categoryCode";
	} 
	
	@RequestMapping(value="/admin/categoryCode/save",method=RequestMethod.POST)
	public String categoryCodeSubmit(HttpServletRequest request,CategoryVo categoryCodeVo,ModelMap model) {
		try {
			Category categoryCode=new Category();
			BeanUtils.copyProperties(categoryCodeVo,categoryCode);
			categoryRepository.save(categoryCode);
			Iterable<Category> categoryCodeList = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", categoryCodeList); 
			model.addAttribute("successMessage","Category Added Successfully."); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

}
