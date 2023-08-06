package com.qxc.gacspboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {
    @RequestMapping({"/","/home","/home.html"})
    public String Index(){
        log.info("开始访问index！！");
        return "index";
    }
}
