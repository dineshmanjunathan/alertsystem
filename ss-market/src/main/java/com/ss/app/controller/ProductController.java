package com.ss.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.app.entity.Category;
import com.ss.app.entity.Product;
import com.ss.app.model.CategoryRepository;
import com.ss.app.model.ProductRepository;
import com.ss.utils.Utils;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping("/loadProduct")
	public ResponseEntity<Iterable<Product>> loadProduct(@RequestParam("categoryId") String category,
			HttpServletRequest request, ModelMap model) {
		List<Product> Product = productRepository.findByCategory(category);
		model.addAttribute("productList", Product);
		return new ResponseEntity(Product, HttpStatus.OK);
	}

	@RequestMapping("/product")
	public String inlogin(HttpServletRequest request, ModelMap model) {
		if(Utils.validateSession(request)) {
			return "index";
		}
		Iterable<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categoryList", categoryList);
		return "productListing";
	}

}
