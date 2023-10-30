package com.svitsmachnogo.api.service.abstractional;

public interface MailSenderService {
     void sendMail(String mailTo, String subject, String message);
}
