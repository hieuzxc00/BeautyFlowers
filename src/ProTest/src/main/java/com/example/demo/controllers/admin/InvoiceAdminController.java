package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.Invoice;
import com.example.demo.services.InvoiceService;

@Controller
@RequestMapping("/admin/invoice")
public class InvoiceAdminController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("invoices", invoiceService.findAllWithOrder());
		return "admin.invoice.index";
	}
	
	@RequestMapping(value = "details/{id}", method = RequestMethod.GET)
	public String details(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("invoice", invoiceService.findById(id));
		return "admin.invoice.details";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		Invoice invoice = invoiceService.findById(id);
		invoice.setStatus("done");
		invoiceService.save(invoice);
		return "redirect:/admin/invoice";
	}
	
}
