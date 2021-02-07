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
import java.util.Arrays;

@Service
public class EmailSendingService {

    private final Logger logger = LoggerFactory.getLogger(EmailSendingService.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendWorksheetEmail(ByteArrayResource file) {
        String[] receivingAddress =  new String[] {"bradenborman@hotmail.com", "amyatkinson19@hotmail.com"};
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            logger.info("Sending email to {}", Arrays.toString(receivingAddress));
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receivingAddress);
            helper.setSubject("Today's Timesheet");
            helper.setText("<p></p>", true);
            helper.addAttachment("todaystimesheet.xlsx", file);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email!");
        }

        javaMailSender.send(message);
    }

}