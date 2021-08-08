package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.services.AccountService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("countNewInvoices", invoiceService.countNewInvoices("pending"));
		modelMap.put("countProducts", productService.count());
		modelMap.put("countCustomers", accountService.countCustomer());
		modelMap.put("countCategories", categoryService.count());
		return "admin.dashboard.index";
	}
	
}
