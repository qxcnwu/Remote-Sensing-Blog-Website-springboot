package com.qxc.gacspboot.dao.Anno;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AutoData {
    boolean fileControl() default true;
    String rootDir() default "";
    boolean value() default true;
}
