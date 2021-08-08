package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.services.AccountService;
import com.example.demo.services.InvoiceService;

@Controller
@RequestMapping("/admin/customer")
public class CustomerAdminController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("customers", accountService.findAllCustomer());
		return "admin.customer.index";
	}
	
	@RequestMapping(value = "invoices/{username}", method = RequestMethod.GET)
	public String invoices(@PathVariable("username") String username, ModelMap modelMap) {
		modelMap.put("invoices", invoiceService.findAllWithOrder(username));
		return "admin.customer.invoices";
	}
	
}
