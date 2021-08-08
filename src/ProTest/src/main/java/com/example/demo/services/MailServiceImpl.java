package com.example.demo.services;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void send(String fromAddress, String toAddress, String subject, String content) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mineMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mineMessageHelper.setFrom(fromAddress);
		mineMessageHelper.setTo(toAddress);
		mineMessageHelper.setSubject(subject);
		mineMessageHelper.setText(content, true);
		mineMessageHelper.setSentDate(new Date());
		javaMailSender.send(mimeMessage);
	}
	
}
