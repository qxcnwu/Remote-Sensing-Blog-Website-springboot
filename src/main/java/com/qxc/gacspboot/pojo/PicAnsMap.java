package com.qxc.gacspboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图像读取数据类
 * @author 邱星晨
 * @since 2022年3月8日
 */
@Data
@AllArgsConstructor
public class PicAnsMap {
    private int row;
    private int col;
    private final Map<String,Integer> id=new HashMap<>();
    private List<Integer> dates;
    private List<String> propNames;
    private final Map<String,List<Float>> answerMap=new HashMap<>();
}
