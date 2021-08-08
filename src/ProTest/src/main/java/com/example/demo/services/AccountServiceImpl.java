package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("Username not found for " + username);
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
		return new User(account.getUsername(), account.getPassword(), grantedAuthorities);
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}


	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account findById(int id) {
		return accountRepository.findById(id);
	}

	@Override
	public List<Account> findAllCustomer() {
		return accountRepository.findAllCustomer(2);
	}

	@Override
	public Long countCustomer() {
		return accountRepository.countCustomer();
	}



}
