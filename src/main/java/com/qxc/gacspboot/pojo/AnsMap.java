package com.qxc.gacspboot.pojo;

import lombok.Data;

@Data
public class AnsMap<T>{
    private String key;
    private T value;
}
