package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.example.demo.paypal.PayPalConFig;

@Service("payPalService")
public class PayPalServiceImpl implements PayPalService{
	
	@Autowired
	private Environment environment;
	
	@Override
	public PayPalConFig getPayPalConFig() {
		PayPalConFig payPalConFig = new PayPalConFig();
		payPalConFig.setAuthToken(environment.getProperty("paypal.authtoken"));
		payPalConFig.setBusiness(environment.getProperty("paypal.business"));
		payPalConFig.setPosturl(environment.getProperty("paypal.posturl"));
		payPalConFig.setReturnurl(environment.getProperty("paypal.returnurl"));
		return payPalConFig;
	}
	
}
