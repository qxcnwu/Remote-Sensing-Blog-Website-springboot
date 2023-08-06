package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.controller.UTiles.UserId;
import com.qxc.gacspboot.dao.RedisDao;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisDao redisDao;

    @Override
    public String AddToken(Login login) {
        String token=UserId.creatToken(UserId.getUUID());
        redisDao.InsertToken(login,token);
        return token;
    }

    @Override
    public Login SearchToken(String token) {
        return redisDao.SelectLogin(token);
    }

    @Override
    public Login SearchToken(HttpServletRequest request) {
        try{
            Cookie[] cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    return SearchToken(cookie.getValue());
                }
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean DeleteToken(String token) {
        return redisDao.Delete(token);
    }
}
