package ru.studentproject.startbusiness.service;

import jakarta.mail.MessagingException;
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
    private MimeMessageHelper helper;

    private MimeMessageHelper createHelper (MimeMessage message) throws MessagingException {
        return  new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
    }
    private Context createContext(Email email){
        Context context = new Context();
        context.setVariables(email.getModel());
        return context;
    }
    private void setEmailSettingsToHelper(Email email) throws MessagingException {
        helper.setTo(email.getTo());
        helper.setFrom(email.getFrom());
        helper.setSubject(email.getSubject());
    }
    public void sendEmail(Email email) {
        try{
            MimeMessage message = emailSender.createMimeMessage();

            helper = createHelper(message);

            Context context = createContext(email);

            String html = templateEngine.process("email-template.html",context);

            setEmailSettingsToHelper(email);

            helper.setText(html,true);

            emailSender.send(message);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
