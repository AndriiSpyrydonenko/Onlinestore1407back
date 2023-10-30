package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.service.abstractional.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;

    public void sendMail(String mailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreplay@gmail.com");
        mailMessage.setTo(mailTo);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
