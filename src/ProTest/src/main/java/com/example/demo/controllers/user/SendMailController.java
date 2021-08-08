package com.example.demo.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.SendMail;

@Controller
@RequestMapping("sendMail")
public class SendMailController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("sendMail", new SendMail());
		return "cart.thanks";
	}
	
	@RequestMapping(value = "send", method = RequestMethod.POST)
	public String send(@ModelAttribute("sendMail") SendMail sendMail, ModelMap modelMap) {
		
		return "cart.thanks";
	}
}
