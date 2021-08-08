package com.example.demo.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.services.ProductService;

@Controller
@RequestMapping(value = {"", "home"})
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("isHome", true);
		modelMap.put("newProducts", productService.newProducts(true, 6));
		modelMap.put("featuredProducts", productService.featuredProducts(true, true, 6));
		return "home.index";
	}
	
}
