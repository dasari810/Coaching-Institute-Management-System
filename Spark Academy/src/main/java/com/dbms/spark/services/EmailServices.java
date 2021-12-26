package com.dbms.spark.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("emailSendService")
public class EmailServices {

	@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(SimpleMailMessage email) {
    	  javaMailSender.send(email); 	
    }
}
