package com.qxc.gacspboot.controller.UTiles;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public R doException(@NotNull Exception ex){
        ex.printStackTrace();
        return new R(ex.getMessage());
    }
}
