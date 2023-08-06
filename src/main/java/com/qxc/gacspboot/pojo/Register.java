package com.qxc.gacspboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 注册对象
 * @author 邱星晨
 * @since 2022年2月22日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_register")
public class Register {
    private int id;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String occupation;
    private String place;
    private int status;
    private Date created;
    private Date last_login;
    private String uuid;
}

