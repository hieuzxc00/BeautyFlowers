package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Invoice;
import com.example.demo.repositories.InvoiceRepository;

@Transactional
@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<Invoice> findAllWithOrder(String username) {
		return invoiceRepository.findAllWithOrder(username);
	}

	@Override
	public Invoice findById(int id) {
		return invoiceRepository.findById(id);
	}

	@Override
	public List<Invoice> findAllWithOrder() {
		return invoiceRepository.findAllWithOrder();
	}

	@Override
	public Long countNewInvoices(String status) {
		return invoiceRepository.countNewInvoices(status);
	}
	
	

}
