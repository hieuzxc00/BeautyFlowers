package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.InvoiceDetails;
import com.example.demo.models.Product;
import com.example.demo.repositories.InvoiceDetailsRepository;

@Transactional
@Service("invoiceDetailsService")
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService{

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public InvoiceDetails save(InvoiceDetails invoiceDetails) {
		Product productInvoice = productService.find(invoiceDetails.getProduct().getId());
		if (productInvoice.getQuantity() < invoiceDetails.getQuantity()) {
			throw new RuntimeException("Not enought quality for buy.");
		}
		int finalQuality = productInvoice.getQuantity() - invoiceDetails.getQuantity();
		productInvoice.setQuantity(finalQuality);
		productService.updateProductQuality(productInvoice);
		return invoiceDetailsRepository.save(invoiceDetails);
	}

}
