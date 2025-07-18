package com.client_control.client_control.services;

import com.client_control.client_control.dtos.user.SendEmailDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(SendEmailDTO dto) {
            SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(dto.from());
                message.setTo(dto.to());
                message.setSubject(dto.subject());
                message.setText(dto.text());
            mailSender.send(message);
    }
}
