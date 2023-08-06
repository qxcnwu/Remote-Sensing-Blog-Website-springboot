package com.qxc.gacspboot.controller.AuthorNeedAnnotion;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorNeed {
    boolean value() default true;
}
