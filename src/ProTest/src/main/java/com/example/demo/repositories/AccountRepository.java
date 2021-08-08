package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Account;

@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<Account, Integer> {

	public Account findByUsername(String username);
	
	Account findById(int id);
	
	@Query("from Account where role.id = :roleId")
	public List<Account> findAllCustomer(@Param("roleId") int roleId);
	
	@Query("select count(id) from Account where role.id = 2")
	public Long countCustomer();
	
}
