package com.qxc.gacspboot.pojo;

import com.qxc.gacspboot.dao.DE.sensor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取定标系数结果结构体
 * @author 邱星晨
 * @since 2022年3月8日
 */
@Data
public class Cc {
    private List<String> name;
    private List<String> date;
    private final Map<String,List<sensor>> map=new HashMap<>();
}
