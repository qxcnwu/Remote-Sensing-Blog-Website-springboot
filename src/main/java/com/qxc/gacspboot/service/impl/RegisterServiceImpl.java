package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.controller.UTiles.UserId;
import com.qxc.gacspboot.dao.RegisterDao;
import com.qxc.gacspboot.pojo.Email;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.Register;
import com.qxc.gacspboot.pojo.Users;
import com.qxc.gacspboot.service.RedisService;
import com.qxc.gacspboot.service.RegisterService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterDao,Register> implements RegisterService {
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private RedisService redisService;

    @Override
    public boolean nameExist(String name) {
        LambdaQueryWrapper<Register> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Register::getUsername,name);
        return registerDao.selectCount(wrapper)!=0;
    }

    @Override
    public Login Login(@NotNull Login login) {
        LambdaQueryWrapper<Register> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Register::getUsername,login.getUsername());
        wrapper.eq(Register::getPassword,login.getPassword());
        wrapper.gt(Register::getStatus,0);
        if(registerDao.selectOne(wrapper)!=null){
            login.setStatus(registerDao.selectOne(wrapper).getStatus());
            return login;
        }else{
            return null;
        }
    }

    @Override
    public boolean save(Register register) {
        int answer=registerDao.insert(register);
        if(answer==0) return false;
        Email email=new Email();
        email.setRecipient(register.getEmail());
        email.setContent(register.getUuid());
        email.setSubject("注册");
        emailService.sendTemMail(email, UserId.getMapper(register));
        return true;
    }

    @Override
    public boolean active(String uuid) {
        LambdaUpdateWrapper<Register> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Register::getUuid,uuid).set(Register::getStatus, 1);
        return registerDao.update(null,wrapper)>0;
    }

    @Override
    public String token(Login login) {
        return redisService.AddToken(login);
    }

    @Override
    public Login getLogin(String token) {
        Login login=null;
        if(!UserId.verifyDate(token)){
            login=redisService.SearchToken(token);
        }
        return login;
    }

    @Override
    public boolean Logout(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,String token) {
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return redisService.DeleteToken(token);
    }

    @Override
    public boolean delCookie(@NotNull HttpServletRequest request, HttpServletResponse response) {
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("token")){
                return Logout(request,response, cookie.getValue());
            }
        }
        return true;
    }

    @Override
    public List<Users> getUsers() {
        List<Users> ans=new ArrayList<>();
        final List<Register> registers = registerDao.selectList(null);
        registers.forEach(i->{
            ans.add(new Users(i.getId(),i.getUsername()));
        });
        return ans;
    }
}
