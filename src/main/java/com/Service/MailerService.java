package com.Service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerService {
    
    // This method sends an email with the given OTP
    public void sendMailForOtp(String email, int otp) {
        // Setup mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Get the session object with authentication
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Use environment variables or a secure method to store the email and password
                return new PasswordAuthentication("prajapativivek949@gmail.com","pcveqsiqovebntbu");
            }
        });
        session.setDebug(true); // Enable debug mode for logging

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email)); // Set the "from" email address
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // Set the recipient
            message.setSubject("OTP For Reset Password"); // Set the email subject
            message.setText("Use the below OTP to reset your password: " + otp); // Set the email body

            // Send the email
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}

