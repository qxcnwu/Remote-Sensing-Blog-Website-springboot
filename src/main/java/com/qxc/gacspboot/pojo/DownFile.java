package com.qxc.gacspboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 应用为1 文章为2 其他为3
 * @author 邱星晨
 * @since 2022年3月9日
 */
@Data
@TableName("tb_downfile")
public class DownFile {
    private int id;
    private String filename;
    private int role;
}
