package com.qxc.gacspboot.controller.UTiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
    private Object data;
    private boolean finish;
    private String message;

    @Contract(pure = true)
    public R(String message) {
        this.message=message;
        this.finish=false;
    }
}
