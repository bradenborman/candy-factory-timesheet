package cftimesheet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void sendTestEmail() {
        String receivingAddress = "bradenborman@hotmail.com";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            logger.info("Sending email to {}", receivingAddress);
            helper.setTo(receivingAddress);
            helper.setSubject("Test Email");
            helper.setText("<h2>TEST EMAIL</h2>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

}