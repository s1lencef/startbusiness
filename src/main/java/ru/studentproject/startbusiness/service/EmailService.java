package ru.studentproject.startbusiness.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.studentproject.startbusiness.models.Email;
import ru.studentproject.startbusiness.models.Employer;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired(required=true)
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(Email email) {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );
            Context context = new Context();
            context.setVariables(email.getModel());
            String html = templateEngine.process("email-template.html",context);
            helper.setTo(email.getTo());
            helper.setFrom(email.getFrom());
            helper.setSubject(email.getSubject());
            helper.setText(html,true);
            emailSender.send(message);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
