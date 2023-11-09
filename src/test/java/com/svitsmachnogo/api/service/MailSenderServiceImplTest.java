package com.svitsmachnogo.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;

import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class MailSenderServiceImplTest {

    @InjectMocks
    private MailSenderServiceImpl mailSenderService;

    @Mock
    private JavaMailSender javaMailSender;

    private static final String MAIL_TO = "you@gmail.com";

    private static final String SUBJECT = "sub";

    private static final String MESSAGE = "some message";

    @Test
    public void sendMailShouldExecuteSendOnce() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MAIL_TO);
        message.setSubject(SUBJECT);
        message.setText(MESSAGE);

        mailSenderService.sendMail(message);

        Mockito.verify(javaMailSender, times(1)).send(message);
    }

    @Test
    public void createMailShouldReturnValidMessage() throws NoSuchMethodException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MAIL_TO);
        message.setSubject(SUBJECT);
        message.setText(MESSAGE);

        SimpleMailMessage returnedMessage = mailSenderService.createMessage(MAIL_TO, SUBJECT, MESSAGE);

        Assertions.assertEquals(
                Arrays.stream(message.getTo()).toList().get(0),
                Arrays.stream(returnedMessage.getTo()).toList().get(0)
        );
        Assertions.assertEquals(message.getSubject(), returnedMessage.getSubject());
        Assertions.assertEquals(message.getText(), returnedMessage.getText());

    }

}