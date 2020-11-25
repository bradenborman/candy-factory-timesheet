package cftimesheet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSendingService {

    private final Logger logger = LoggerFactory.getLogger(EmailSendingService.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendTestEmail(ByteArrayResource file) {
        String receivingAddress = "bradenborman@hotmail.com";
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            logger.info("Sending email to {}", receivingAddress);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receivingAddress);
            helper.setSubject("Test Email");
            helper.setText("<h2>TEST EMAIL</h2>", true);
            helper.addAttachment("todaystimesheet.xlsx", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

}