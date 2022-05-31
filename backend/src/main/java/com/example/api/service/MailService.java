package com.example.api.service;

import com.example.api.model.Request;
import com.example.api.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final String BASE_URL = "http://localhost:8080";

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultiPart, boolean isHtml) {
        logger.info("Sending email");

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom("hamed@najah.edu");
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch (Exception ex) {
            logger.warn("Email could not be sent to user '{}': {}", to, ex.getMessage());
        }
    }

    @Async
    public void sendEmailFromTemplate(User dean, User student, Request currentRequest, String templateName, String subject) {
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("dean", dean);
        context.setVariable("student", student);
        context.setVariable("request", currentRequest);
        context.setVariable("baseURL", BASE_URL);

        String content = templateEngine.process(templateName, context);
        sendEmail(dean.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendRequestEmail(User dean, User student, Request currentRequest) {
        logger.debug("Sending email to '{}'", dean.getEmail());
        sendEmailFromTemplate(dean, student, currentRequest, "email/emailToDean", "CAP Registration Assistant");
    }


}
