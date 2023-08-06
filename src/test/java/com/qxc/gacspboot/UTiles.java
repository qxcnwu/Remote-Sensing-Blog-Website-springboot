package com.qxc.gacspboot;

import com.qxc.gacspboot.controller.UTiles.UserId;
import com.qxc.gacspboot.pojo.Email;
import com.qxc.gacspboot.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class UTiles{
    @Autowired
    private EmailService emailService;

    @Test
    public void sendTemplateMail() {
        Email email=new Email();
        email.setRecipient("2081896628@qq.com");
        email.setSubject("gac");
        email.setContent("cewcewcw");
        Map<String,Object> map=new HashMap<>();
        map.put("username","qxcnwu");
        map.put("uuid", UserId.getUUID());
        System.out.println(map.get("uuid"));
        emailService.sendTemMail(email,map);
    }
}
