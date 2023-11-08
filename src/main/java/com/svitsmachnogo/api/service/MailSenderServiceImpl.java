package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.service.abstractional.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * A service class that implements the MailSenderService interface for sending email messages.
 */
@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;

    /**
     * Sends an email using the provided SimpleMailMessage object.
     *
     * @param mailMessage The SimpleMailMessage object that contains information about the email.
     * @author Vanya Demydenko
     */
    public void sendMail(SimpleMailMessage mailMessage) {
        mailSender.send(mailMessage);
    }

    /**
     * Creates a SimpleMailMessage object with the specified recipient, subject, and message.
     *
     * @param mailTo  The recipient's email address.
     * @param subject The subject of the email.
     * @param message The body of the email.
     * @return A SimpleMailMessage object ready for sending.
     * @author Vanya Demydenko
     */
    public SimpleMailMessage createMessage(String mailTo, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        return simpleMailMessage;
    }
}
