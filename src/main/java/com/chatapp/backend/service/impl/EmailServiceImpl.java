package com.chatapp.backend.service.impl;

import com.chatapp.backend.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Async
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(String to, String firstName, String link) {
        String email = buildEmail(firstName, link);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("chattapp741@gmail.com");
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    private String buildEmail(String name, String link) {
        return
                String.format("""
                        <div style="border: 1px solid black; padding: 20px; border-radius: 5px">
                            <h2>Confirm your email</h2>
                            <p>Thanks for signing up to ChattApp, %s!</p>
                            <p>Please click on the below link to activate your account:</p>
                            <p><a href=%s>Activate Account</a></p>
                            <p>Thanks,</p>
                            <p>The ChattApp Team</p>
                        </div>
                        """, name, link);
    }
}
