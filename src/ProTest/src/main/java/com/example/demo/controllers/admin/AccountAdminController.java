package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Account;
import com.example.demo.services.AccountService;

@Controller
@RequestMapping("/admin/account")
public class AccountAdminController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String profile(Authentication authentication, ModelMap modelMap) {
		modelMap.put("account", accountService.findByUsername(authentication.getName()));
		return "admin.account.profile";
	}
	
	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public String profile(@ModelAttribute("account") Account account) {
		Account currentAccount = accountService.findById(account.getId());
		if(!account.getPassword().isEmpty()) {
		String hash = new BCryptPasswordEncoder().encode(account.getPassword());
		currentAccount.setPassword(hash);
		}
		currentAccount.setUsername(account.getUsername());
		currentAccount.setEmail(account.getEmail());
		currentAccount.setFullName(account.getFullName());
		currentAccount.setPhone(account.getPhone());
		currentAccount.setAddress(account.getAddress());
		currentAccount.setUsername(account.getUsername());
		accountService.save(currentAccount);
		return "redirect:/admin/account/profile";
	}
	
}
