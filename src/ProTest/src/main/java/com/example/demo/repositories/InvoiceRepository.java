package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Invoice;

@Repository("invoiceRepository")
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

	@Query("from Invoice where account.username = :username order by id desc")
	public List<Invoice> findAllWithOrder(@Param("username") String username);
	
	Invoice findById(int id);
	
	@Query("from Invoice order by id desc")
	public List<Invoice> findAllWithOrder();
	
	@Query("select count(id) from Invoice where status = :status")
	public Long countNewInvoices(@Param("status") String status);
}
