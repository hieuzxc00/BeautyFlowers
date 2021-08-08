package com.example.demo.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Account;
import com.example.demo.models.Role;
import com.example.demo.services.AccountService;
import com.example.demo.services.InvoiceService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(ModelMap modelMap) {
		modelMap.put("customer", new Account());
		return "customer.register";
	}	
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("customer") Account account, ModelMap modelMap) {
		try {
			String hash = new BCryptPasswordEncoder().encode(account.getPassword());
			account.setPassword(hash);
			account.setStatus(true);
			account.setRole(new Role(2));
			accountService.save(account);
			return "redirect:/customer-panel";
		} catch (Exception e) {
			modelMap.put("err", e.getMessage());
			return "customer.register";
		}
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String dashboard(ModelMap modelMap) {
		return "customer.dashboard";
	}
	
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(Authentication authentication, ModelMap modelMap) {
		modelMap.put("customer", accountService.findByUsername(authentication.getName()));
		return "customer.profile";
	}	
	
	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public String profile(@ModelAttribute("customer") Account account, ModelMap modelMap) {
		try {
			Account currentAccount = accountService.findById(account.getId());
			if(!account.getPassword().isEmpty()) {
				String hash = new BCryptPasswordEncoder().encode(account.getPassword());
				currentAccount.setPassword(hash);
			}
			currentAccount.setAddress(account.getAddress());
			currentAccount.setEmail(account.getEmail());
			currentAccount.setFullName(account.getFullName());
			currentAccount.setPhone(account.getPhone());
			accountService.save(currentAccount);
			return "redirect:/customer/dashboard";
		} catch (Exception e) {
			modelMap.put("err", e.getMessage());
			return "customer.profile";
		}
	}
	
	@RequestMapping(value = "history", method = RequestMethod.GET)
	public String history(Authentication authentication, ModelMap modelMap) {
		modelMap.put("invoices", invoiceService.findAllWithOrder(authentication.getName()));
		return "customer.history";
	}	
	
	@RequestMapping(value = "invoicedetails/{id}", method = RequestMethod.GET)
	public String invoicedetails(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("invoice", invoiceService.findById(id));
		return "customer.invoicedetails";
	}
	
}
