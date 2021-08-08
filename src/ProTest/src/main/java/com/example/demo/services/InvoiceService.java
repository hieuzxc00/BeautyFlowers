package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Invoice;

public interface InvoiceService {
	
	public Invoice save(Invoice invoice);
	
	public List<Invoice> findAllWithOrder(String username);

	public Invoice findById(int id);
	
	public List<Invoice> findAllWithOrder();
	
	public Long countNewInvoices(String status);
}
