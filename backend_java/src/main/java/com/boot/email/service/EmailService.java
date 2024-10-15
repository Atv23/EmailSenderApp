package com.boot.email.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	// Importing username and password from .env file
	@Value("${EMAIL_USERNAME}")
	private String username;
	@Value("${EMAIL_PASSWORD}")
	private String password;
	@Value("${EMAIL_USERNAME}")
	private String from;
	
	public boolean sendEmail(String recepient, String message, String subject) {

		// 2. Configure SMTP Properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Address of SMTP server (gmail/yahoo/outlook,etc.)
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// 3. Create a Mail Session with Authentication
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true); // For debugging

		// 4. Compose the email [text,multi-media]
		MimeMessage msg = new MimeMessage(session);
		try {
			// adding sender's email
			msg.setFrom(from);
			// adding subject to message
			msg.setSubject(subject);
			// adding text to message
			msg.setText(message);
			// adding recipient to message
			InternetAddress recipientAddress = new InternetAddress(recepient);
			recipientAddress.validate();
			msg.addRecipient(Message.RecipientType.TO, recipientAddress);

			// 5. Send the e-mail using Transport class
			Transport.send(msg);
			System.out.println("Sent success...................");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("OOPS! Mail couldn't be sent, Error: " + e);
			return false;
		}
		return true;
	}
}
