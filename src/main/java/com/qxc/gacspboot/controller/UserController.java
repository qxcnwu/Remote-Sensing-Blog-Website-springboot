package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.Config.NginxConfig;
import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.controller.UTiles.UserId;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.Register;
import com.qxc.gacspboot.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private NginxConfig nginxConfig;

    @RequestMapping("/all")
    public R getAll(){
        R r=new R();
        r.setData(registerService.getUsers());
        r.setFinish(true);
        return r;
    }

    @RequestMapping("/register")
    public R Register(@NotNull Register register){
        R r=new R();
        register.setUuid(UserId.getUUID());
        r.setFinish(registerService.save(register));
        r.setMessage("激活邮件已经发送到您的邮箱！！");
        return r;
    }

    @RequestMapping("/nameExist")
    public R nameExist(String name){
        R r=new R();
        r.setData(registerService.nameExist(name));
        r.setFinish(true);
        return r;
    }

    @RequestMapping("/login")
    public R Login(Login login, HttpServletResponse response,HttpServletRequest request){
        R r=new R();
        login=registerService.Login(login);
        r.setFinish(login!=null);
        r.setData(login==null?null:registerService.token(login));
        Cookie cookie=new Cookie("token", (String) r.getData());
        cookie.setPath("/");
        if(login!=null){
            log.info("登陆成功");
            response.addCookie(cookie);
            return r;
        }
        //登陆失败删除cookie
        registerService.delCookie(request,response);
        return r;
    }

    @RequestMapping("/active/{uuid}")
    public String Active(@PathVariable String uuid, @NotNull HttpServletResponse response){
        R r=new R();
        r.setFinish(registerService.active(uuid));
        r.setMessage("账户激活成功");
        try{
            response.sendRedirect("http://"+nginxConfig.getIp()+":"+nginxConfig.getPort()+"/login");
        }catch (IOException ex){
            log.error(ex.getMessage());
            return "redirect:"+nginxConfig.getIp()+":"+nginxConfig.getPort()+"/wrongPage";
        }
        return "";
    }

    @AuthorNeed
    @RequestMapping("/logout")
    public R logout(HttpServletRequest request,HttpServletResponse response,@CookieValue("token") String token){
        R r=new R();
        r.setFinish(registerService.Logout(request,response,token));
        return r;
    }

}
