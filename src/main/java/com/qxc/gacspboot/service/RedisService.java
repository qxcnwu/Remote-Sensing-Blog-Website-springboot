package com.qxc.gacspboot.service;

import com.qxc.gacspboot.pojo.Login;

import javax.servlet.http.HttpServletRequest;

public interface RedisService {
    public String AddToken(Login login);
    public Login SearchToken(String token);
    public Login SearchToken(HttpServletRequest request);
    public boolean DeleteToken(String token);
}
