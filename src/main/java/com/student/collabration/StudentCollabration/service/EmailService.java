package com.student.collabration.StudentCollabration.service;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public EmailService(JavaMailSender javaMailSender) {

    }

    public void sendEmail(String to, String subject, String templateName, Map<String, Object> model) throws MessagingException, IOException, TemplateException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Load the HTML template
        Template template = freemarkerConfig.getTemplate(templateName);

        // Process the template with the model data
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);

        // Set the email content
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(stringWriter.toString(), true);

        // Send the email
        javaMailSender.send(message);
    }


}
