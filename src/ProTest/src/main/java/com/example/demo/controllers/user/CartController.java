package com.example.demo.controllers.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Account;
import com.example.demo.models.Invoice;
import com.example.demo.models.InvoiceDetails;
import com.example.demo.models.InvoiceDetailsId;
import com.example.demo.models.Item;
import com.example.demo.paypal.PayPalResult;
import com.example.demo.paypal.PayPalSuccess;
import com.example.demo.services.AccountService;
import com.example.demo.services.InvoiceDetailsService;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.PayPalService;
import com.example.demo.services.ProductService;

import scala.util.Success;


@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceDetailsService invoiceDetailsService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private PayPalService payPalService;
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpSession session, ModelMap modelMap) {
		int countItems = 0;
		double total = 0;
		if(session.getAttribute("cart") != null) {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			countItems = cart.size();
			modelMap.put("cardItems", cart);
			for(Item item : cart) {
				total += item.getProduct().getPrice() * item.getQuantity();
			}
		}
		modelMap.put("countItems", countItems);
		modelMap.put("total", total);
		modelMap.put("payPalConfig", payPalService.getPayPalConFig());
		return "cart.index";
	}
	
	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public String buy(@PathVariable("id") int id, HttpSession session) {
		if(session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			cart.add(new Item(productService.find(id), 1));
			session.setAttribute("cart", cart);
		}else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = exists(id, cart);
			if(index == -1) {
				cart.add(new Item(productService.find(id), 1));
			}else {
				int newQuantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(newQuantity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/index";
	}
	
	@RequestMapping(value = "remove/{index}", method = RequestMethod.GET)
	public String remove(@PathVariable("index") int index, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/index";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestParam("quantities") int[] quantities, HttpSession session) {
		if(session.getAttribute("cart") != null) {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			for(int i = 0; i < cart.size(); i++) {
				cart.get(i).setQuantity(quantities[i]);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/index";
	}
	
	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public String confirm(Authentication authentication, ModelMap modelMap) {
		if(authentication == null) {
			return "redirect:/customer-panel";
		}else {
		modelMap.put("customer", accountService.findByUsername(authentication.getName()));
		return "cart.confirm";
		}
	}
	
	@RequestMapping(value = "confirm", method = RequestMethod.POST)
	public String confirm(@ModelAttribute("customer") Account account, ModelMap modelMap, HttpSession session) {
		try {
			
			Account currentAccount = accountService.findById(account.getId());
			currentAccount.setAddress(account.getAddress());
			currentAccount.setEmail(account.getEmail());
			currentAccount.setFullName(account.getFullName());
			currentAccount.setPhone(account.getPhone());
			accountService.save(currentAccount);
			
			int countItems = 0;
			double total = 0;
			if(session.getAttribute("cart") != null) {
				List<Item> cart = (List<Item>) session.getAttribute("cart");
				countItems = cart.size();
				modelMap.put("cardItems", cart);
				for(Item item : cart) {
					total += item.getProduct().getPrice() * item.getQuantity();
				}				
			}
			
			
			//Todo send checkout email
			SimpleMailMessage smm = new SimpleMailMessage();
//			smm.setFrom("hieu-checkout@gmail.com");
			smm.setSubject("Beauty Flowers Order Confirmation");
			smm.setTo(currentAccount.getEmail());
			StringBuilder mailContent = new StringBuilder();
			mailContent.append("Hi " + currentAccount.getFullName() + "!");
			mailContent.append("\n");
			mailContent.append("Your Phone: " + currentAccount.getPhone() + "");
			mailContent.append("\n");
			mailContent.append("Your address: " + currentAccount.getAddress() + "");
			mailContent.append("\n");
			mailContent.append("****************************");
			mailContent.append("\n");
			mailContent.append("Total: " + total + "$");
			mailContent.append("\n");
			mailContent.append("Your order will be processed and delivered as soon as possible");
			mailContent.append("\n");
			mailContent.append("Thanks so much!");
			mailContent.append("\n");
			mailContent.append("****************************");
			mailContent.append("\n");
			mailContent.append("Follow Fanpage on Facebook: https://www.facebook.com/Beauty-Flowers-114024883792584");
			mailContent.append("\n");
			mailContent.append("Hotline: 0359313750");
			smm.setText(mailContent.toString());
			javaMailSender.send(smm);
			return "redirect:/cart/checkout"; 
		}catch(Exception e) {
			modelMap.put("err", e.getMessage());
			return "cart.confirm";
		}
	}
	
	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success(HttpServletRequest request, HttpSession session) {
		PayPalSuccess payPalSuccess = new PayPalSuccess();
		PayPalResult payPalResult = payPalSuccess.getPayPal(request, payPalService.getPayPalConFig());
		System.out.println("Order Info");
		System.out.println("First Name: " + payPalResult.getFirst_name());
		System.out.println("Last Name: " + payPalResult.getLast_name());
		System.out.println("Country: " + payPalResult.getAddress_country());
		System.out.println("Address: " + payPalResult.getAddress_name());
		System.out.println("Email: " + payPalResult.getPayer_email());	
		System.out.println("Gross: " + payPalResult.getMc_gross());
		
		//remove cart
		session.removeAttribute("cart");
		return "cart.thanks";
	}
	
	@RequestMapping(value = "checkout2", method = RequestMethod.GET)
	@Transactional
	public String checkout2(Authentication authentication, HttpSession session) {
			if(session.getAttribute("cart") != null) {
				//save new invoice
				Invoice invoice = new Invoice();
				invoice.setAccount(accountService.findByUsername(authentication.getName()));
				invoice.setCreated(new Date());
				invoice.setName("New Invoice");
				invoice.setStatus("paid by Paypal");
				invoice = invoiceService.save(invoice);
				
				//save invoice details
				List<Item> cart = (List<Item>) session.getAttribute("cart");
				for(Item item : cart) {
					double productQualityWithPrice = item.getProduct().getPrice() * item.getQuantity();
					InvoiceDetails invoiceDetails = new InvoiceDetails();
					invoiceDetails.setId(new InvoiceDetailsId(invoice.getId(), item.getProduct().getId()));
					invoiceDetails.setPrice(productQualityWithPrice);
					invoiceDetails.setQuantity(item.getQuantity());
					invoiceDetails.setProduct(item.getProduct());
					invoiceDetailsService.save(invoiceDetails);
				}
				//remove cart
				session.removeAttribute("cart");
			}
			
			return "cart.thanks";
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	@Transactional
	public String checkout(Authentication authentication, HttpSession session) {
		if(authentication == null) {
			return "redirect:/customer-panel";
		}else {
			if(session.getAttribute("cart") != null) {
				//save new invoice
				Invoice invoice = new Invoice();
				invoice.setAccount(accountService.findByUsername(authentication.getName()));
				invoice.setCreated(new Date());
				invoice.setName("New Invoice");
				invoice.setStatus("pending");
				invoice = invoiceService.save(invoice);
				
				//save invoice details
				List<Item> cart = (List<Item>) session.getAttribute("cart");
				for(Item item : cart) {
					double productQualityWithPrice = item.getProduct().getPrice() * item.getQuantity();
					InvoiceDetails invoiceDetails = new InvoiceDetails();
					invoiceDetails.setId(new InvoiceDetailsId(invoice.getId(), item.getProduct().getId()));
					invoiceDetails.setPrice(productQualityWithPrice);
					invoiceDetails.setQuantity(item.getQuantity());
					invoiceDetails.setProduct(item.getProduct());
					invoiceDetailsService.save(invoiceDetails);
				}
				
				//remove cart
				session.removeAttribute("cart");
				
			}
			
			return "cart.thanks";
		}
	}
	
	private int exists(int id, List<Item> cart) {
		for(int i = 0; i < cart.size(); i++) {
			if(cart.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
}
