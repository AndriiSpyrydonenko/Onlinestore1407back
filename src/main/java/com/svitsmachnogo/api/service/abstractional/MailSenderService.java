package com.svitsmachnogo.api.service.abstractional;

import org.springframework.mail.SimpleMailMessage;

/**
 * An interface representing a service for sending email messages.
 */
public interface MailSenderService {

     /**
      * Sends an email using the provided SimpleMailMessage object.
      *
      * @param mailMessage The SimpleMailMessage object that contains information about the email.
      * @author Vanya Demydenko
      */
     void sendMail(SimpleMailMessage mailMessage);

     /**
      * Creates a SimpleMailMessage object with the specified recipient, subject, and message.
      *
      * @param mailTo  The recipient's email address.
      * @param subject The subject of the email.
      * @param message The body of the email.
      * @return A SimpleMailMessage object ready for sending.
      * @author Vanya Demydenko
      */
     SimpleMailMessage createMessage(String mailTo, String subject, String message);
}
