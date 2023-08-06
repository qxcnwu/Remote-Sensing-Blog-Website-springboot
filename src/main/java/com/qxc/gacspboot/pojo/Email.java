package com.qxc.gacspboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件主题类
 * @author 邱星晨
 * @since 2022年2月22日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容
}
