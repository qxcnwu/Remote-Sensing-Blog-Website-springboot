package com.qxc.gacspboot.dao;

import com.qxc.gacspboot.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean InsertToken(Login login, String Token){
        redisTemplate.opsForValue().set(Token, login);
        return true;
    }

    public Login SelectLogin(String token){
        return (Login) redisTemplate.opsForValue().get(token);
    }

    public boolean Delete(String token){
        return Boolean.TRUE.equals(redisTemplate.delete(token));
    }
}
