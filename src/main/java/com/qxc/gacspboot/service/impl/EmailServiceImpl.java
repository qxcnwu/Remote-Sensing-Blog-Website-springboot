package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.pojo.Email;
import com.qxc.gacspboot.service.EmailService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.transform.Templates;
import java.io.File;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMail(@NotNull Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        javaMailSender.send(message);
    }

    @Override
    public void sendHtmlMail(@NotNull Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try{
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            messageHelper.setText(email.getContent(), true);
            javaMailSender.send(message);
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("邮件发送异常！",e);
        }
    }

    @Override
    public void sendAttachmentsMail(@NotNull Email email, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email.getRecipient());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }

    @Override
    public void sendTemMail(@NotNull Email email, Map<String,Object> vars) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try{
            logger.info("邮件发送中");
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            Context context = new Context();
            context.setVariables(vars);
            String content = templateEngine.process("mail", context);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("邮件发送异常！",e);
        }

    }
}
