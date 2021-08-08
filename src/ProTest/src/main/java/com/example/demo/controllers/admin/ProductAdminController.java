package com.example.demo.controllers.admin;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.helpers.ZXingHelper;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("products", productService.findAll());
		return "admin.product.index";
	}
	
	@RequestMapping(value = "qrcode/{id}", method = RequestMethod.GET)
	public void qrcode(@PathVariable("id") int id, HttpServletResponse response) throws Exception {
		Product product = productService.find(id);
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getQRCodeImage
				("Beauty Flowers" + "\n" + "Product Id: " + id + "\n" + "Product Name: " + product.getName() + "\n" + "Price: " + product.getPrice() + "$" + "\n" + "Qty: " + product.getQuantity(), 200, 200));
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping(value = "barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 200, 100));
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		Product product = new Product();
		product.setStatus(true);
		modelMap.put("product", product);
		modelMap.put("categories", categoryService.findParentCatagories());
		return "admin.product.add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("product") Product product, ModelMap modelMap) {
		productService.save(product);
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		try {
			productService.delete(id);
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", "Deleted Failed");
		}
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.put("product", productService.find(id));
		modelMap.put("categories", categoryService.findParentCatagories());
		return "admin.product.edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("product") Product product) {
		productService.save(product);
		return "redirect:/admin/product";
	}
	
}
