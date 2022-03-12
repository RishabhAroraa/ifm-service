package com.imageflowmeta.service.email;


import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender javaMailSender;


    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            mimeMessageHelper.setText(email, true);

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Confirm you email!");

            // TODO
            mimeMessageHelper.setFrom("");

            javaMailSender.send(mimeMessage);

        } catch(MessagingException messagingException) {
            throw new IllegalStateException("failed to send mail!");
        }
    }
}
