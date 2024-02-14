package com.student.collabration.StudentCollabration;

import com.student.collabration.StudentCollabration.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class StudentCollabrationApplication {

	private final EmailService emailService;

	public StudentCollabrationApplication(EmailService emailService) {
		this.emailService = emailService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentCollabrationApplication.class, args);
	}

	@Bean
	public EmailService emailServiceBean(JavaMailSender javaMailSender) {
		return new EmailService(javaMailSender);
	}

	@Autowired
	public void configureMailSender(JavaMailSenderImpl mailSender) {
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("chattingapp001@gmail.com");
		mailSender.setPassword("wasglpzwdmmsnbwj");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
