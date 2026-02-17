package com.Sankalp.Blog_Backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    public void send(String to,String subject,String body){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
    }

    public void sendWelcomeEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome!");
        message.setText("Thank you for registering.");
        javaMailSender.send(message);
    }
}
