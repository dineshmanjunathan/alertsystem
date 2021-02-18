package com.ss.app.controller;

import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;

import com.ss.app.entity.Category;
import com.ss.app.entity.KYCDetails;
import com.ss.app.entity.Member;
import com.ss.app.entity.Notification;
import com.ss.app.entity.Product;
import com.ss.app.entity.SSConfiguration;
import com.ss.app.entity.StockPointPurchase;
import com.ss.app.entity.WithdrawnPoints;
import com.ss.app.model.AddressRepository;
import com.ss.app.model.CategoryRepository;
import com.ss.app.model.KYCDetailsRepository;
import com.ss.app.model.NotificationRepository;
import com.ss.app.model.ProductRepository;
import com.ss.app.model.SSConfigRepository;
import com.ss.app.model.StockPointPurchaseRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.model.WithdrawnPointsRepository;
import com.ss.app.vo.CategoryVo;
import com.ss.app.vo.MemberVo;
import com.ss.app.vo.ProductVo;
import com.ss.app.vo.SSConfigurationVo;
import com.ss.utils.Utils;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SSConfigRepository ssConfigRepo;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private StockPointPurchaseRepository stockPurchaseRepository;

	@Autowired
	private WithdrawnPointsRepository withdrawnPointsRepository;

	@Autowired
	private KYCDetailsRepository kycDetailsRepository;
	
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

	@RequestMapping("/admin/menu")
	public String adminMenu(HttpServletRequest request, ModelMap model) {
		model.addAttribute("ROLE", "ADMIN");

		Iterable<Member> memberList = userRepository.findAll();
		if (memberList != null) {
			Utils utils = new Utils();
			HashMap<String, Long> countMap = utils.computeMemberCount(memberList);
			model.mergeAttributes(countMap);
		}

		return "adminMenu";
	}

	@RequestMapping("/admin/member/listing")
	public String adminListing(HttpServletRequest request, ModelMap model) {
		Iterable<Member> memberList = userRepository.getAllMemberWithStatus();
		model.addAttribute("memberList", memberList);
		return "memberListing";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String stockPointLoginSubmit(HttpServletRequest request, MemberVo user, ModelMap model) {
		try {
			Member member = userRepository.findByIdAndPasswordAndRole(user.getId(), user.getPassword(), "ADMIN").get();
			if (member != null && member.getId() != null) {
				request.getSession().setAttribute("LOGGED_ON", "true");
				request.getSession().setAttribute("MEMBER_ID", user.getId());
				request.getSession().setAttribute("MEMBER_NAME", member.getName());
				request.getSession().setAttribute("ROLE", member.getRole());
				request.getSession().setAttribute("REFERENCE_CODE", member.getReferencecode());

				Iterable<Member> memberList = userRepository.findAll();
				if (memberList != null) {
					Utils utils = new Utils();
					HashMap<String, Long> countMap = utils.computeMemberCount(memberList);
					model.mergeAttributes(countMap);
				}

				return "adminMenu";
			} else {
				request.getSession().setAttribute("ROLE", "ADMIN");
				model.addAttribute("errormsg", "User Id or Password is incorrect!");
			}
		} catch (Exception e) {
			request.getSession().setAttribute("ROLE", "ADMIN");
			model.addAttribute("errormsg", "Admin does not Exists!");
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

			addressRepository.deleteByMember_Id(userId);
			notificationRepository.deleteByMember_Id(userId);
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
	public String categoryCodeListing(HttpServletRequest request, ModelMap model) {
		try {
			List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
			model.addAttribute("categoryCodeList", categoryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

	@RequestMapping(value = "/admin/categoryCode/delete", method = RequestMethod.GET)
	public String categoryCodeDelete(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			try {
				categoryRepository.deleteByCode(id);
			} catch (DataIntegrityViolationException e) {
				List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
				model.addAttribute("categoryCodeList", categoryCodeList);
				model.addAttribute("errormessage", "Product avaialbe for this category.");
				return "categoryCodeListing";
			}
			model.addAttribute("deletesuccessmessage", "Category Deleted Successfully.");
			List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
			model.addAttribute("categoryCodeList", categoryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

	@RequestMapping(value = "/admin/categoryCode/edit", method = RequestMethod.GET)
	public String categoryCodeEdit(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			Category categoryCode = categoryRepository.findByCode(id);
			CategoryVo categoryCodeVo = new CategoryVo();
			BeanUtils.copyProperties(categoryCode, categoryCodeVo);
			model.addAttribute("categoryCode", categoryCodeVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCode";
	}

	@RequestMapping(value = "/admin/categoryCode/edit", method = RequestMethod.POST)
	public String categoryCodeEditSubmit(HttpServletRequest request, CategoryVo categoryCodeVo, ModelMap model) {
		Category categoryCode = new Category();
		try {
			BeanUtils.copyProperties(categoryCodeVo, categoryCode);
			categoryRepository.save(categoryCode);
			List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
			model.addAttribute("categoryCodeList", categoryCodeList);
			model.addAttribute("successMessage", "Category Updated Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

	@RequestMapping("/admin/categoryCode")
	public String incategoryCode(HttpServletRequest request, ModelMap model) {
		return "categoryCode";
	}

	@RequestMapping(value = "/admin/categoryCode/save", method = RequestMethod.POST)
	public String categoryCodeSubmit(HttpServletRequest request, CategoryVo categoryCodeVo, ModelMap model) {
		try {
			Category categoryCode = new Category();
			BeanUtils.copyProperties(categoryCodeVo, categoryCode);
			categoryRepository.save(categoryCode);
			List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
			model.addAttribute("categoryCodeList", categoryCodeList);
			model.addAttribute("successMessage", "Category Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryCodeListing";
	}

	@RequestMapping("/admin/productListing")
	public String productListing(HttpServletRequest request, ModelMap model) {
		try {
			setActiveProductListToModelMap(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productListing";
	}

	@RequestMapping(value = "/admin/product/delete", method = RequestMethod.GET)
	public String productDelete(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			productRepository.updateToInactiveProduct(id);
			model.addAttribute("deletesuccessmessage", "Product Deleted Successfully.");
			setActiveProductListToModelMap(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productListing";
	}

	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.GET)
	public String productEdit(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			Product productCode = productRepository.findByCode(id);
			model.addAttribute("productCode", productCode);
			List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
			model.addAttribute("categoryCodeList", categoryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "product";
	}

	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.POST)
	public String productEditSubmit(HttpServletRequest request, ProductVo productVo, ModelMap model,
			@RequestParam(required = false) MultipartFile image) {
		Product product = new Product();
		try {
			BeanUtils.copyProperties(productVo, product);
			if (!image.isEmpty()) {
				byte[] imageByte = image.getBytes();
				product.setImage(imageByte);
			} else if (productVo.getBase64Image() != null) {
				product.setImage(Base64.getDecoder().decode(productVo.getBase64Image()));
			}
			productRepository.save(product);
			setActiveProductListToModelMap(model);
			model.addAttribute("successMessage", "Product Updated Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productListing";
	}

	@RequestMapping("/admin/product")
	public String inproduct(HttpServletRequest request, ModelMap model) {

		List<Category> categoryCodeList = categoryRepository.findAllByOrderByCode();
		model.addAttribute("categoryCodeList", categoryCodeList);

		return "product";
	}

	@RequestMapping(value = "/admin/product/save", method = RequestMethod.POST)
	public String categoryCodeSubmit(HttpServletRequest request, ProductVo productVo, ModelMap model,
			@RequestParam(required = false) MultipartFile image) {
		try {
			Product product = new Product();
			Product productCode = productRepository.findByCode(productVo.getCode());
			if (productCode != null && productCode.getCode() != null) {
				setActiveProductListToModelMap(model);
				model.addAttribute("errormsg", "Product code [" + productCode.getCode() + "] already exist!");
				return "productListing";
			}

			BeanUtils.copyProperties(productVo, product);
			if (image != null) {
				byte[] imageByte = image.getBytes();
				product.setImage(imageByte);
			}
			productRepository.save(product);
			setActiveProductListToModelMap(model);
			model.addAttribute("successMessage", "Product Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productListing";
	}

	@RequestMapping("/admin/stockpurchase/listing")
	public String stockpurchaseListing(HttpServletRequest request, ModelMap model) {
		Iterable<StockPointPurchase> purchaseList = stockPurchaseRepository.findAll();
		model.addAttribute("stockPoitPurchaseList", purchaseList);
		return "stockPointSalesHistory";
	}

	@RequestMapping("/admin/ssconfig/listing")
	public String ssconfigListing(HttpServletRequest request, ModelMap model) {
		Iterable<SSConfiguration> ssConfig = ssConfigRepo.findAll();
		model.addAttribute("ssConfigList", ssConfig);
		return "ssConfigList";
	}

	@RequestMapping(value = "/admin/ssconfig/edit", method = RequestMethod.GET)
	public String ssconfigEdit(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		SSConfiguration ssConfig = ssConfigRepo.findById(id).get();
		model.addAttribute("ssConfigDetail", ssConfig);
		model.addAttribute("ssConfigType", Utils.getSSConfigTypeList());
		return "ssConfigEdit";
	}

	@RequestMapping(value = "/admin/ssconfig/edit", method = RequestMethod.POST)
	public String ssconfigEditSubmit(HttpServletRequest request, SSConfigurationVo ssConfigurationVo, ModelMap model) {
		SSConfiguration ssConfiguration = new SSConfiguration();
		try {
			BeanUtils.copyProperties(ssConfigurationVo, ssConfiguration);
			ssConfiguration.setDescription(Utils.getSSConfigTypeMap().get(ssConfiguration.getCode()));
			ssConfigRepo.save(ssConfiguration);
			Iterable<SSConfiguration> ssConfig = ssConfigRepo.findAll();
			model.addAttribute("ssConfigList", ssConfig);
			model.addAttribute("successMessage", "Configuration Updated Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ssConfigList";
	}

	@RequestMapping(value = "/admin/ssconfig/delete", method = RequestMethod.GET)
	public String ssconfigDelete(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			Long val = ssConfigRepo.deleteByCode(id);
			if (val > 0) {
				model.addAttribute("deletesuccessmessage", id + " - Configuration Deleted Successfully.");
			} else {
				model.addAttribute("deletesuccessmessage", id + " - Unable to Delete");
			}
			Iterable<SSConfiguration> ssConfig = ssConfigRepo.findAll();
			model.addAttribute("ssConfigList", ssConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ssConfigList";
	}

	@RequestMapping(value = "/admin/ssconfig/save", method = RequestMethod.POST)
	public String ssconfigSave(HttpServletRequest request, SSConfigurationVo ssConfigurationVo, ModelMap model) {
		try {
			SSConfiguration ssConfiguration = new SSConfiguration();
			BeanUtils.copyProperties(ssConfigurationVo, ssConfiguration);
			ssConfiguration.setDescription(Utils.getSSConfigTypeMap().get(ssConfiguration.getCode()));
			ssConfigRepo.save(ssConfiguration);
			Iterable<SSConfiguration> ssConfig = ssConfigRepo.findAll();
			model.addAttribute("ssConfigList", ssConfig);
			model.addAttribute("successMessage", "Configuration Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ssConfigList";
	}

	@RequestMapping("/admin/ssconfig")
	public String inSSConfig(HttpServletRequest request, ModelMap model) {

		model.addAttribute("ssConfigType", Utils.getSSConfigTypeList());
		return "ssConfigEdit";
	}

	@RequestMapping(value = "/admin/withdrawnPointHistory/list", method = RequestMethod.GET)
	public String withdrawnPointsHistory(HttpServletRequest request, ModelMap model) {
		try {
			Iterable<WithdrawnPoints> withdrawnPointsHistoryList = withdrawnPointsRepository.findAll();
			model.addAttribute("withdrawnPointsHistoryList", withdrawnPointsHistoryList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "withdrawnPointTxnHistory";
	}

	@RequestMapping(value = "/admin/memberWithdrawnApproval/list", method = RequestMethod.GET)
	public String approveWithdrawnPoints(HttpServletRequest request, ModelMap model) {
		try {
			List<WithdrawnPoints> withdrawnPointsHistoryList = withdrawnPointsRepository.findByStatus("PENDING");
			model.addAttribute("withdrawnPointsHistoryList", withdrawnPointsHistoryList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "memberWithdrawnApproval";
	}

	@RequestMapping(value = "/admin/withdrawn/approve", method = RequestMethod.GET)
	public String approveWithdrawnTxn(HttpServletRequest request, ModelMap model, @RequestParam("id") String id) {
		try {
			WithdrawnPoints withdrawnPoints = withdrawnPointsRepository.findById(Long.parseLong(id)).get();

			if (withdrawnPoints != null && withdrawnPoints.getId() != null) {
				withdrawnPoints.setStatus("APPROVED");
				model.addAttribute("successMessage", "Approved Successfully.");
				withdrawnPoints = withdrawnPointsRepository.save(withdrawnPoints);

				List<WithdrawnPoints> withdrawnPointsHistoryList = withdrawnPointsRepository.findByStatus("PENDING");
				model.addAttribute("withdrawnPointsHistoryList", withdrawnPointsHistoryList);
			} else {
				model.addAttribute("errorMessage", "Try Again Later!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "memberWithdrawnApproval";
	}

	@RequestMapping(value = "/admin/kyc/list", method = RequestMethod.GET)
	public String getkycList(HttpServletRequest request, ModelMap model) {
		try {
			List<KYCDetails> kycList = kycDetailsRepository.findByStatus("PENDING");
			model.addAttribute("kycDetails", kycList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "kycDetailsListing";
	}

	@RequestMapping(value = "/admin/kyc/approve", method = RequestMethod.GET)
	public String approveKyc(HttpServletRequest request, ModelMap model, @RequestParam("id") String id) {
		try {
			KYCDetails kycDetails = kycDetailsRepository.findById(Long.parseLong(id)).get();

			if (kycDetails != null && kycDetails.getId() != null) {
				kycDetails.setStatus("APPROVED");
				kycDetails = kycDetailsRepository.save(kycDetails);
				model.addAttribute("successMessage", "KYC Details Approved Successfully.");

				Member member = userRepository.findById(kycDetails.getMemberId()).get();
				member.setPancardNumber(kycDetails.getPancardNumber());
				userRepository.save(member);

				List<KYCDetails> kycList = kycDetailsRepository.findByStatus("PENDING");
				model.addAttribute("kycDetails", kycList);
			} else {
				model.addAttribute("errorMessage", "Try Again Later!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "kycDetailsListing";
	}
	
	@RequestMapping(value = "/admin/kyc/reject", method = RequestMethod.GET)
	public String rejectKyc(HttpServletRequest request, ModelMap model, @RequestParam("id") String id) {
		try {
			KYCDetails kycDetails = kycDetailsRepository.findById(Long.parseLong(id)).get();
			Member member = userRepository.findById(kycDetails.getMemberId()).get();
			kycDetailsRepository.deleteById(Long.parseLong(id));

			Notification notification=setNotificationSMS("Pan details are incorrect, please upload correct Pan Card",member);
			notificationRepository.save(notification);
			
			List<KYCDetails> kycList = kycDetailsRepository.findByStatus("PENDING");
			model.addAttribute("kycDetails", kycList);
			model.addAttribute("successMessage", "KYC Rejected Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "kycDetailsListing";
	}

	/**
	 * @param model
	 */
	private void setActiveProductListToModelMap(ModelMap model) {
		List<Product> productList = productRepository.getActiveProducts();
		model.addAttribute("productListing", productList);
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
