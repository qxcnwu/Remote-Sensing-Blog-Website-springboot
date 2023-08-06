package com.qxc.gacspboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login implements Serializable {
    private String username;
    private String password;
    private int status;
}
