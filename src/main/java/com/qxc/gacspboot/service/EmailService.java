package com.qxc.gacspboot.service;

import com.qxc.gacspboot.pojo.Email;

import java.util.Map;

public interface EmailService {
    void sendSimpleMail(Email email);
    void sendHtmlMail(Email email);
    void sendAttachmentsMail(Email email,String filePath);
    void sendTemMail(Email email, Map<String,Object> map);
}
