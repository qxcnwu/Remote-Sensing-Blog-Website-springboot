package com.qxc.gacspboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 博客类
 * @author 邱星晨
 * @since 2022年2月22日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_blog")
public class Blog {
    private int id;
    private String userid;
    private String title;
    private String description;
    private String content;
    private String words;
    private String created;
    private int status;
    private int statuscode;
    private String fileid;
    private int type;
    private String wsid;
}
