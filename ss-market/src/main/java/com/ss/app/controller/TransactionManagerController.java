package com.ss.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;
import com.ss.app.entity.Address;
import com.ss.app.entity.Cart;
import com.ss.app.entity.Category;
import com.ss.app.entity.Member;
import com.ss.app.entity.Product;
import com.ss.app.entity.Purchase;
import com.ss.app.entity.RewardTransaction;
import com.ss.app.entity.SSConfiguration;
import com.ss.app.entity.StockPointProduct;
import com.ss.app.entity.StockPointPurchase;
import com.ss.app.model.AddressRepository;
import com.ss.app.model.CartRepository;
import com.ss.app.model.CategoryRepository;
import com.ss.app.model.ProductRepository;
import com.ss.app.model.PurchaseRepository;
import com.ss.app.model.RewardTransactionRepository;
import com.ss.app.model.SSConfigRepository;
import com.ss.app.model.StockPointProuctRepository;
import com.ss.app.model.StockPointPurchaseRepository;
import com.ss.app.model.UserRepository;
import com.ss.app.vo.AddressVo;
import com.ss.utils.OrderPDFExporter;
import com.ss.utils.ReportGenerator;
import com.ss.utils.Utils;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@Transactional(rollbackOn = Exception.class)
public class TransactionManagerController {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StockPointProuctRepository stockPointProuctRepository;

	@Autowired
	private StockPointPurchaseRepository stockPointPurchaseRepository;

	@Autowired
	private RewardTransactionRepository rewardTransactionRepository;

	@Autowired
	private SSConfigRepository ssConfigRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(value = "/purchase/confirm", method = RequestMethod.POST)
	public String savePurchase(HttpServletRequest request, AddressVo address, ModelMap model) {
		try {
			// update active days date in member table
			if("EPAY".equals(address.getPaymentType())) {
				// redirect to payment page
			}
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			List<Cart> cart = cartRepository.findByMemberid(memberId);
			// Get order number
			Long orderNumber = Utils.getOrderNumber();
			Purchase purchase = new Purchase();
			Member member = userRepository.findById(memberId).get();
			Long totalQty = 0L;
			Long activeDays = 0L;
			purchase.setPaymentType(address.getPaymentType());

			if(address.getPaymentType()!=null && address.getPaymentType().equals("REPURCHASE")) {		
				Double carttotal =Double.parseDouble(address.getCartTotal());
				purchase.setRedeemedPoints(carttotal.longValue());
				address.setRedeemedPoints(carttotal.longValue());
			}else {
				address.setRedeemedPoints(0L);
			}
			ArrayList<String> categoryCodelist = new ArrayList<String>();
			
			for (Cart c : cart) {
				// Update qty in product
				Product prod = productRepository.findByCode(c.getCode());
				if (prod.getQuantity() <= 0) {
					cartRepository.deleteByCodeAndMemberid(prod.getCode(), memberId);
					model.addAttribute("errormsg", "Item out of stock !");
					return purchaseReview(request, model);
				}
				prod.setQuantity(prod.getQuantity() - c.getQuantity());
				productRepository.save(prod);

				// Prepare purchase
				preparePurchase(request.getSession(), member, orderNumber, purchase, c, prod);
				
				totalQty = totalQty + c.getQuantity();
				if(!categoryCodelist.contains(prod.getCategory().getCode())) {
					categoryCodelist.add(prod.getCategory().getCode());
					activeDays = activeDays + prod.getCategory().getActivedays();
				}
			}
			
			// Save address
			Address add = new Address();
			BeanUtils.copyProperties(address, add);
			add.setMember(member);
			add.setOrderNumber(orderNumber);
			addressRepository.save(add);
			
			cartRepository.deleteByMemberid(memberId);
			
			if(address.getRedeemedPoints() > 0) {
				member.setRepurcahse(member.getRepurcahse() - address.getRedeemedPoints());
			}

			// Reward Customer.
			if (request.getSession() != null && request.getSession().getAttribute("ROLE").equals("MEMBER")) {
				rewardCustomer(request,member.getId(), member.getReferedby(), orderNumber, totalQty, activeDays);
			}

			// TODO email to member email address
			model.addAttribute("cartList", cart);
			model.addAttribute("address", address);
			model.addAttribute("orderNumber", orderNumber);
			model.addAttribute("successMessage", "Item Purchased Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseConfirmation";
	}

	@RequestMapping(value = "/purchase/manual/confirm", method = RequestMethod.GET)
	public String saveManualPurchase(HttpServletRequest request, ModelMap model,
			@RequestParam(required = true) String memberid) {
		try {
			HttpSession session = request.getSession();
			String memberId = (String) session.getAttribute("MEMBER_ID");
			// String role = (String) session.getAttribute("ROLE");
			Member member = null;
			try {
				member = userRepository.findById(memberid).get();
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Invalid member ID , Please provide a valid one.");
				purchasemanualReview(request, model);
			}

			List<Cart> cart = cartRepository.findByMemberid(memberId);
			// Get order number
			Long orderNumber = Utils.getOrderNumber();
			Purchase purchase = new Purchase();
			Long totalQty = 0L;
			Long activeDays = 0L;
			ArrayList<String> categoryCodelist = new ArrayList<String>();
			for (Cart c : cart) {
				// Update qty in product
				Product product = null;
				StockPointProduct prod = stockPointProuctRepository.findByCode(c.getCode());
				if (prod.getQuantity() <= 0) {
					cartRepository.deleteByCodeAndMemberid(prod.getCode(), memberId);
					model.addAttribute("errormsg", "Item out of stock !");
					return purchasemanualReview(request, model);
				}
				prod.setQuantity(prod.getQuantity() - c.getQuantity());
				stockPointProuctRepository.save(prod);

				// Prepare purchase
				product = new Product();
				product.setCode(prod.getCode());
				product.setProdDesc(prod.getProdDesc());
				prepareManualPurchase(session, member, orderNumber, purchase, c, product);
				totalQty = totalQty + c.getQuantity();
				
				if(!categoryCodelist.contains(prod.getCategory().getCode())) {
					categoryCodelist.add(prod.getCategory().getCode());
					activeDays = activeDays + prod.getCategory().getActivedays();
				}
			}
			cartRepository.deleteByMemberid(memberId);

			// Reward Customer.
			if (request.getSession() != null && request.getSession().getAttribute("ROLE").equals("MEMBER")) {
				rewardCustomer(request,member.getId(), member.getReferedby(), orderNumber, totalQty, activeDays);
			}

			model.addAttribute("cartList", cart);
			model.addAttribute("orderNumber", orderNumber);
			model.addAttribute("memberId", member.getId());
			model.addAttribute("successMessage", "Item Purchased Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseManualConfirmation";
	}

	private void preparePurchase(HttpSession session, Member member, Long orderNumber, Purchase purchase, Cart c,
			Product prod) {
		purchase.setOrderNumber(orderNumber);
		purchase.setAmount(c.getAmount());
		purchase.setMemberid(member.getId());
		if ("STOCK_POINT".equals(member.getRole())) {
			StockPointProduct spp = new StockPointProduct();
			spp.setCategory(prod.getCategory());
			spp.setCode(prod.getCode());
			spp.setMemberId(member.getId());
			spp.setPrice(prod.getPrice());
			spp.setProdDesc(prod.getProdDesc());
			spp.setQuantity(c.getQuantity());
			spp.setImage(prod.getImage());
			spp.setStatus("PENDING");
			spp.setOrderNumber(orderNumber);
			stockPointProuctRepository.save(spp);
		} else {
			purchase.setOrderStatus("DELIVERED");
		}
		purchase.setProduct(prod);
		purchase.setQuantity(c.getQuantity());
		purchaseRepository.save(purchase);
	}

	private void prepareManualPurchase(HttpSession session, Member member, Long orderNumber, Purchase purchase, Cart c,
			Product prod) {
		String memberId = (String) session.getAttribute("MEMBER_ID");
		purchase.setOrderStatus("PENDING");
		StockPointPurchase sp = new StockPointPurchase();
		sp.setStockPointId(memberId);
		sp.setPrice(c.getAmount());
		sp.setMemberId(member.getId());
		sp.setProductCode(prod);
		sp.setQty(c.getQuantity());
		stockPointPurchaseRepository.save(sp);
	}

	private void rewardCustomer(HttpServletRequest request,String memId, String sponserId, Long orderNumber, Long totalQty, Long activeDays) {
		RewardTransaction reward = new RewardTransaction();
		try {
			Member actualMember = userRepository.findByIdAndRole(memId,"MEMBER").get();
			if (actualMember != null && actualMember.getId() != null) {
				if (actualMember.getActive_days() != null) {
					actualMember.setActive_days(actualMember.getActive_days().plusDays(totalQty * activeDays));
				} else {
					actualMember.setActive_days(LocalDateTime.now().plusDays(totalQty * activeDays));
				}
				actualMember =userRepository.save(actualMember);
				
				if(actualMember.getActive_days() !=null) {
					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss");
					java.util.Date date = Date.from(actualMember.getActive_days().atZone(ZoneId.systemDefault()).toInstant());
					request.getSession().setAttribute("ACTIVE_DAYS", sdf.format(date));
				} else {
					LocalDateTime time = LocalDateTime.now();
					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss");
					java.util.Date date = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
					request.getSession().setAttribute("ACTIVE_DAYS", sdf.format(date));
				}			
			}
			
			Member member = userRepository.findByReferencecodeAndRole(sponserId,"MEMBER").get();
			if (member != null && member.getId() != null) {
				reward.setMemberid(memId);
				SSConfiguration ssConfig = ssConfigRepository.findById("PR").get();
				reward.setPoint(ssConfig.getValue());
				reward.setOrderNumber(orderNumber);
				reward.setSponserId(sponserId);
				reward.setRewardedMember(member.getId());
				RewardTransaction response = rewardTransactionRepository.save(reward);

				if (member != null && member.getId() != null && response != null && response.getMemberid() != null) {
					
					if (ssConfig.getValue() > 0) {
						member.setWalletBalance(member.getWalletBalance() + ssConfig.getValue().longValue());
					}
					userRepository.save(member);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping(value = "/purchase/detail", method = RequestMethod.GET)
	public String loadPurchase(HttpServletRequest request, ModelMap model) {
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			List<Purchase> purchaseList = purchaseRepository.findByMemberid(memberId);
			model.addAttribute("purchaseList", purchaseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseDetails";
	}

	@RequestMapping(value = "/purchase/list", method = RequestMethod.GET)
	public String purchaseList(HttpServletRequest request, ModelMap model) {
		try {
			setActiveProductListToModelMap(model); 
			Iterable<Category> catIterable = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", catIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseProductList";
	}

	@RequestMapping(value = "/purchase/loadProduct/{catId}", method = RequestMethod.GET)
	public String loadProduct(@PathVariable String catId, HttpServletRequest request, ModelMap model) {
		try {
			Iterable<Product> productList = productRepository.findByCategory(catId);
			model.addAttribute("productList", productList);
			Iterable<Category> catIterable = categoryRepository.findAll();
			model.addAttribute("categoryCodeList", catIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseProductList";
	}

	@RequestMapping(value = "/purchase/review", method = RequestMethod.GET)
	public String purchaseReview(HttpServletRequest request, ModelMap model) {
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			List<Cart> cart = cartRepository.findByMemberid(memberId);
			model.addAttribute("cartList", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseReview";
	}

	@RequestMapping(value = "/purchase/address", method = RequestMethod.GET)
	public String purchaseAddress(HttpServletRequest request, ModelMap model) {
		try {
			String userId = (String) request.getSession().getAttribute("MEMBER_ID");
			if (userId != null && !userId.isEmpty()) {
				Member mem = userRepository.findById(userId).get();
				if (mem != null && mem.getId() != null) {
					
					Double cartTotal=cartRepository.getCartTotal(userId);
					model.addAttribute("member", mem);
					model.addAttribute("cartTotal", cartTotal);
					return "address";
				}
			} else {
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "login";
	}

	@RequestMapping(value = "/purchase/manual/review", method = RequestMethod.GET)
	public String purchasemanualReview(HttpServletRequest request, ModelMap model) {
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			List<Cart> cart = cartRepository.findByMemberid(memberId);
			model.addAttribute("cartList", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseManualReview";
	}

	@RequestMapping(value = "/purchase/review/edit", method = RequestMethod.GET)
	public String reviewEdit(HttpServletRequest request, ModelMap model) {
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			List<Cart> cart = cartRepository.findByMemberid(memberId);
			Double total = 0.0;
			if (cart != null) {
				Map<String, Long> map = new HashMap<>();
				for (Cart c : cart) {
					total = total + (c.getQuantity() * c.getAmount());
					map.put(c.getCode(), c.getQuantity());
				}
				
				model.addAttribute("cartMap", map);
				model.addAttribute("cartTotal", total);
			}
			setActiveProductListToModelMap(model); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseProductList";
	}

	@RequestMapping(value = "/purchase/manual/edit", method = RequestMethod.GET)
	public String reviewManualEdit(HttpServletRequest request, ModelMap model) {
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			List<Cart> cart = cartRepository.findByMemberid(memberId);
			Double total = 0.0;
			if (cart != null) {
				Map<String, Long> map = new HashMap<>();
				for (Cart c : cart) {
					total = total + (c.getQuantity() * c.getAmount());
					map.put(c.getCode(), c.getQuantity());
				}
				;
				model.addAttribute("cartMap", map);
				model.addAttribute("cartTotal", total);
			}
			Iterable<StockPointProduct> productList = stockPointProuctRepository.findByMemberIdAndStatus(memberId,"DELIVERED");
			model.addAttribute("productList", productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manualPurchase";
	}

	@RequestMapping(value = "/purchase/addToCart", method = RequestMethod.POST)
	public ResponseEntity<String> addTocart(HttpServletRequest request, ModelMap model,
			@RequestParam("prodCode") String prodCode, @RequestParam("qty") String qty) {
		Double cartTotal = 0.0;
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			Cart existingCart = cartRepository.findByMemberidAndCode(memberId, prodCode);
			if (existingCart != null) {
				existingCart.setQuantity(Long.parseLong(qty));
				cartRepository.save(existingCart);
			} else {
				Product product = productRepository.findByCode(prodCode);
				Cart cart = new Cart();
				cart.setCode(product.getCode());
				cart.setProdDesc(product.getProdDesc());
				cart.setMemberid(memberId);
				cart.setQuantity(Long.parseLong(qty));
				cart.setAmount(product.getPrice());
				cartRepository.save(cart);
			}
			cartTotal = cartRepository.getCartTotal(memberId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("0.0", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(String.valueOf(cartTotal), HttpStatus.OK);
	}

	@RequestMapping(value = "/purchase/remove/cart", method = RequestMethod.POST)
	public ResponseEntity<String> removeFromCarty(HttpServletRequest request, ModelMap model,
			@RequestParam("prodCode") String prodCode) {
		Double cartTotal = 0.0;
		try {
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			Long val = cartRepository.deleteByCodeAndMemberid(prodCode, memberId);
			if (val <= 0) {
				return new ResponseEntity<String>("No product to remove", HttpStatus.BAD_REQUEST);
			}
			cartTotal = cartRepository.getCartTotal(memberId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("0.0", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(String.valueOf(cartTotal), HttpStatus.OK);
	}

	@RequestMapping(value = "/purchase/allmanual/list", method = RequestMethod.GET)
	public String allManualTxnList(HttpServletRequest request, ModelMap model) {
		try {
			Iterable<Purchase> purchaseList = purchaseRepository.findByMember("STOCK_POINT");
			model.addAttribute("purchaseList", purchaseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "allManualTransactionList";
	}

	@RequestMapping(value = "/purchase/allMember/list", method = RequestMethod.GET)
	public String allMemberTxnList(HttpServletRequest request, ModelMap model) {
		try {
			Iterable<Purchase> purchaseList = purchaseRepository.findByMember("MEMBER");
			model.addAttribute("purchaseList", purchaseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "allMemberTransactionList";
	}

	@RequestMapping(value = "/purchase/pending/list", method = RequestMethod.GET)
	public String pendingTxnList(HttpServletRequest request, ModelMap model) {
		try {
			Iterable<Purchase> purchaseList = purchaseRepository.findByOrderStatus("PENDING");
			model.addAttribute("purchaseList", purchaseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trasnactionApprove";
	}

	@RequestMapping(value = "/purchase/approve", method = RequestMethod.GET)
	public String approvePurchase(HttpServletRequest request, ModelMap model, @RequestParam("id") String id) {
		try {
			Purchase purchase = purchaseRepository.findById(Long.parseLong(id)).get();

			if (purchase != null && purchase.getId() != null) {
				purchase.setOrderStatus("DELIVERED");
				model.addAttribute("successMessage", "Order " + purchase.getOrderNumber() + " Delivered Successfully.");
				purchase = purchaseRepository.save(purchase);

				StockPointProduct stockPointProduct = stockPointProuctRepository
						.findByOrderNumber(purchase.getOrderNumber());
				stockPointProduct.setStatus("DELIVERED");
				stockPointProuctRepository.save(stockPointProduct);

				Iterable<Purchase> purchaseList = purchaseRepository.findByOrderStatus("PENDING");
				model.addAttribute("purchaseList", purchaseList);

			} else {
				model.addAttribute("errorMessage", "Try Again Later!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trasnactionApprove";
	}

	//@RequestMapping(value = "/purchase/order/generate/pdf", method = RequestMethod.GET)
	public void export(@RequestParam("orderNumber") String orderNumber, ModelMap model, HttpServletResponse response) {
		try {
			JasperPrint jasperPrint = null;
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", String.format("attachment; filename=" + orderNumber + ".pdf"));

			List<Purchase> purchaseList = purchaseRepository.findByOrderNumber(Long.parseLong(orderNumber));

			OutputStream out = response.getOutputStream();
			ReportGenerator reportGenerator = new ReportGenerator();
			jasperPrint = reportGenerator.getJasperContext(reportGenerator.getPurchaseReportData(purchaseList),
					"templates/purchaseOrder.jrxml");
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/purchase/order/generate/pdf")
	public void exportToPDF(@RequestParam("orderNumber") String orderNumber, HttpServletResponse response)
			throws DocumentException, IOException {
		try {
			response.setContentType("application/pdf");
			
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=order_report_" + orderNumber + ".pdf";
			response.setHeader(headerKey, headerValue);

			List<Purchase> purchaseList = purchaseRepository.findByOrderNumber(Long.parseLong(orderNumber));
			Purchase purchase = purchaseList.get(0);
			Address address = addressRepository.findByOrderNumber(Long.parseLong(orderNumber));
			
			OrderPDFExporter exporter = new OrderPDFExporter(purchaseList, address);
			exporter.export(response, purchase.getMemberid(), orderNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/purchase/reward/history", method = RequestMethod.GET)
	public String getRewardHistory(@RequestParam("id") String memberId, HttpServletRequest request, ModelMap model) {
		try {
			Iterable<RewardTransaction> rewardhistory = rewardTransactionRepository.findByRewardedMemberWithLimit(memberId);
			model.addAttribute("REQMEMBERID", memberId);
			model.addAttribute("rewardList", rewardhistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "memberRewardHistory";
	}	
	/**
	 * @param model
	 */
	private void setActiveProductListToModelMap(ModelMap model) {
		List<Product> productList = productRepository.getActiveProducts();
		model.addAttribute("productList", productList);
	}
}
