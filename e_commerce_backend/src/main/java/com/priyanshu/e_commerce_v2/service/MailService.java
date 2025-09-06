package com.priyanshu.e_commerce_v2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Async
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${sender.mail.address}")
    private String senderMail;

    @Value("${bcc.mail.address}")
    private String backupMail;

    public void sendMail(String to, String subject, String body) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(senderMail);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        mail.setBcc(backupMail);

        mailSender.send(mail);
    }

}
