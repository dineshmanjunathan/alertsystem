package com.ss.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.app.entity.Category;
import com.ss.app.entity.Member;
import com.ss.app.entity.StockPointProduct;
import com.ss.app.entity.StockPointPurchase;
import com.ss.app.model.CategoryRepository;
import com.ss.app.model.StockPointProuctRepository;
import com.ss.app.model.StockPointPurchaseRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.MemberVo;

@Controller
public class StockPointController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private StockPointPurchaseRepository stockPointPurchaseRepository;
	
	@Autowired
	private StockPointProuctRepository stockPointProuctRepository;
	
	@RequestMapping("/stock/point/login")
	public String inlogin(HttpServletRequest request,ModelMap model) {
		model.addAttribute("ROLE","STOCK_POINT");
		if (request.getSession() != null && request.getSession().getAttribute("MEMBER_ID")!=null) {
			request.getSession().invalidate();
		}
		return "commonLogin";
	} 
	
	@RequestMapping("/stock/point/menu")
	public String toStockMenu(HttpServletRequest request,ModelMap model) {
		return "stockPointMenu";
	}
	
	@RequestMapping("/stock/point/salehistory")
	public String stockSalesHistory(HttpServletRequest request,ModelMap model) {
		String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
		Iterable<StockPointPurchase> spList = stockPointPurchaseRepository.findByStockPointId(memberId);
		model.addAttribute("stockPointPurchaseList",spList);
		return "stockPointSalesHistory";
	} 
	
	@RequestMapping("/stock/point/inventory")
	public String stockPointInventory(HttpServletRequest request,ModelMap model) {
		String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
		Iterable<StockPointProduct> spList = stockPointProuctRepository.findByMemberId(memberId);
		model.addAttribute("stockPointInventory",spList);
		return "stockPointInventory";
	} 
	
	@RequestMapping(value="/stock/point/login",method=RequestMethod.POST)
	public String stockPointLoginSubmit(HttpServletRequest request,MemberVo user,ModelMap model) {
		try {
			Member member = userRepository.findByIdAndPasswordAndRole(user.getId(), user.getPassword(), "STOCK_POINT").get();
			if(member!=null && member.getId() !=null) {
				request.getSession().setAttribute("LOGGED_ON", "true");
				request.getSession().setAttribute("MEMBER_ID", user.getId());
				request.getSession().setAttribute("MEMBER_NAME", member.getName());	
				request.getSession().setAttribute("ROLE", member.getRole());
				return "stockPointMenu";
			} else {
				model.addAttribute("ROLE","STOCK_POINT");
				model.addAttribute("errormsg","User Id or Password is incorrect!");
			}
		} catch (Exception e) {
			model.addAttribute("ROLE","STOCK_POINT");
			model.addAttribute("errormsg","Stock point does not Exists!");
		}
		return "commonLogin";
	}
	
	@RequestMapping(value="/stock/point/manual/purchase",method=RequestMethod.GET)
	public String manualPurchase(HttpServletRequest request, ModelMap model) {
		try {
			Iterable<Category> categoryList = categoryRepository.findAll();
			model.addAttribute("categoryList", categoryList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manualPurchase";
	}

}
