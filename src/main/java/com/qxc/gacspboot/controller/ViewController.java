package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
@Slf4j
public class ViewController {

    @AuthorNeed
    @RequestMapping("/1")
    public String View(){
        log.info("开始访问view1！！");
        return "views/Blog";
    }

    @RequestMapping("/2")
    public String View2(){
        log.info("开始访问view2！！");
        return "views/Blog";
    }
}
