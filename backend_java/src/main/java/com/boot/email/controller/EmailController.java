package com.boot.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.email.model.EmailContent;
import com.boot.email.model.EmailResponse;
import com.boot.email.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired private EmailService emailService;
	 
	@GetMapping("/welcome")
	public String welcome()
	{
		return "hello, this is my Email API";
	}
	
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody EmailContent content){
		
		System.out.println(content);
		// 1. Preparing the content of the mail
		boolean success = this.emailService.sendEmail(content.getTo(), content.getMessage(), content.getSubject());
		if(success)
			return new ResponseEntity<>(new EmailResponse("Email Sent Successfully!!"), HttpStatus.OK);
		return new ResponseEntity<>(new EmailResponse("Email not sent"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
