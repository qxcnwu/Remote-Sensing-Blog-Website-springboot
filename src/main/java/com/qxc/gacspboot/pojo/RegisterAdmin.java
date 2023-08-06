package com.qxc.gacspboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 管理员对象
 * @author 邱星晨
 * @since 2022年2月22日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_registeradmin")
public class RegisterAdmin {
    private int id;
    private String username;
    private String password;
    private int status;
    private Date creates;
    private Date last_login;
}
