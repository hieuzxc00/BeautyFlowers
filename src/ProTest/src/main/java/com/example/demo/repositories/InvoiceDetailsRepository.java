package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.InvoiceDetails;
import com.example.demo.models.InvoiceDetailsId;

@Repository("invoiceDetailsRepository")
public interface InvoiceDetailsRepository extends CrudRepository<InvoiceDetails, InvoiceDetailsId> {

	
}
