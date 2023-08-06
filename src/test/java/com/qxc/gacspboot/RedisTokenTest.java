package com.qxc.gacspboot;

import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class RedisTokenTest {
    @Autowired
    private RedisService redisService;
    private final ArrayList<String> arrayList=new ArrayList<>();
    @Test
    void testAdd(){
        for(int i=0;i<100;i++){
            Login login=new Login();
            login.setUsername("qxc");
            login.setPassword("123");
            login.setStatus(11);
            arrayList.add(redisService.AddToken(login));
        }
        System.out.println(arrayList);
    }

    @Test
    void Select(){
        Login login=redisService.SearchToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1dWlkIiwiZXhwIjoxNjQ1Njk5MzY5LCJ1c2VySWQiOiIwNDc4ZmYyMS05OWRlLTRmN2QtOGQyYS05MTNlNDc5YzBmZGQiLCJpYXQiOjE2NDU2MTI5Njl9.moi2_dEKFAcxb5FDGXTvhR2mKJkhHcrV_j9cd5UGx7w");
        System.out.println(login);
    }

}
