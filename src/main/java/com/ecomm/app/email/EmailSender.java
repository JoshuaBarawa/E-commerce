package com.ecomm.app.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	
	public String sendEmail(String to, String email) {
			
			String toEmail = to;
			String fromEmail = "dminjoshuabm@gmail.com";
			String password = "Bank@123";
			try {
		    Properties prop = new Properties();	
		    prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		    prop.setProperty("mail.smtp.port", "465");
		    prop.setProperty("mail.smtp.auth", "true");
		    prop.setProperty("mail.smtp.ssl.enable", "true");
		   
		    Session session = Session.getInstance(prop, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});
		    
		    session.setDebug(true);
		    
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(fromEmail));
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		    message.setSubject("Confirm Your Email");
		    message.setText("Please Confirm Your Email Address " + email);
		    
		    Transport.send(message);
		    
		    System.out.println("Sending Email..........");
		    System.out.println("Email sent sent succesful to: " + email);
			}
			catch(Exception e) {
				e.getStackTrace();
			}
			
			return "Email has been sent to your account";
	}
}
