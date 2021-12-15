package com.DSD.HAWK.PROJECT.lib;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSender {
    JavaMailSender mailSender;
    @Async
    public void send(String receiver,String msg){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(msg, true);
            helper.setTo(receiver);
            helper.setSubject("Confirm your email");
            helper.setFrom("dummyjaswinder97@gmail.com");
            mailSender.send(mimeMessage);
        } catch (Exception e) {

            System.out.println("failed to send email. An error occured err:"+e);
        }
    }
}
