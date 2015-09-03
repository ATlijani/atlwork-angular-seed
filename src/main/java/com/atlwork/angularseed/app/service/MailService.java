package com.atlwork.angularseed.app.service;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.atlwork.angularseed.app.domain.User;

@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private Environment env;

    private String mailFrom;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void init() {
	this.mailFrom = env.getProperty("mail.from");
    }

    @Async
    public void sendEmail(String mailTo, String subject, String content, boolean isMultipart, boolean isHtml) {

	log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}", isMultipart, isHtml, mailTo, subject, content);

	MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
	try {
	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
	    message.setTo(mailTo);
	    message.setFrom(mailFrom);
	    message.setSubject(subject);
	    message.setText(content, isHtml);
	    javaMailSenderImpl.send(mimeMessage);
	} catch (Exception e) {
	    log.warn("E-mail could not be sent to user '{}', exception is: {}", mailTo, e.getMessage());
	}
    }

    @Async
    public void sendActivationEmail(User user, String baseUrl) {

	Context context = new Context(Locale.ENGLISH);
	context.setVariable("user", user);
	context.setVariable("baseUrl", baseUrl);
	String content = templateEngine.process("activationEmail", context);
	String subject = messageSource.getMessage("email.activation.title", null, Locale.ENGLISH);
	sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendPasswordResetMail(User user, String baseUrl) {

	Context context = new Context(Locale.ENGLISH);
	context.setVariable("user", user);
	context.setVariable("baseUrl", baseUrl);
	String content = templateEngine.process("passwordResetEmail", context);
	String subject = messageSource.getMessage("email.reset.title", null, Locale.ENGLISH);
	sendEmail(user.getEmail(), subject, content, false, true);
    }
}
