package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.RegisterAdminService;
import com.qxc.gacspboot.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/zjucasqxc")
public class AdminController {
    @Autowired
    private RegisterAdminService registerAdminService;
    @Autowired
    private RegisterService registerService;

    @AuthorNeed
    @GetMapping("/all")
    private @NotNull R All(){
        R r=new R();
        r.setData(registerService.list());
        r.setFinish(true);
        return r;
    }

    @PostMapping("/login")
    public R Login(Login login){
        R r=new R();
        r.setFinish(registerAdminService.Login(login));
        r.setData(login);
        log.error(String.valueOf(r));
        return r;
    }
}
