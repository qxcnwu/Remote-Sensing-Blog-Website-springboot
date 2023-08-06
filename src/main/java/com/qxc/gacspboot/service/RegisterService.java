package com.qxc.gacspboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.Register;
import com.qxc.gacspboot.pojo.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface RegisterService extends IService<Register> {
    public boolean nameExist(String name);
    public Login Login(Login login);
    public boolean save(Register register);
    public boolean active(String uuid);
    public String token(Login login);
    public Login getLogin(String token);
    public boolean Logout(HttpServletRequest request,HttpServletResponse response,String token);
    public boolean delCookie(HttpServletRequest request,HttpServletResponse response);

    public List<Users> getUsers();
}
