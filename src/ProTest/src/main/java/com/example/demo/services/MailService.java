package com.example.demo.services;

public interface MailService {
	
	public void send(String fromAddress, String toAddress, String subject, String content) throws Exception;
	
}
