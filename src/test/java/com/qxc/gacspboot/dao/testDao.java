package com.qxc.gacspboot.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qxc.gacspboot.pojo.Register;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;

@SpringBootTest
public class testDao {
    @Autowired
    private RegisterDao registerDao;

    @Test
    void testInsert(){
        for(int i=0;i<20;i++){
            Random random=new Random();
            Register register=new Register();
            register.setUsername(String.valueOf(random.nextInt()));
            register.setEmail(String.valueOf(random.nextInt()));
            register.setPassword(String.valueOf(random.nextInt()));
            register.setOccupation(String.valueOf(random.nextInt()));
            register.setPlace(String.valueOf(random.nextInt()));
            Date date=new Date();
            register.setCreated(date);
            register.setPhone(String.valueOf(random.nextInt()));

            registerDao.insert(register);
        }

    }

    @Test
    void testSelect(){
        registerDao.selectList(null);
    }

    @Test
    void testDelete(){
        registerDao.deleteById(1);
    }

    @Test
    void testPage(){
        IPage<Register> page=new Page<>(2,5);
        page=registerDao.selectPage(page,null);
        page.getRecords();
        System.out.println(page.getPages());
    }

    @Test
    void testBy(){
        QueryWrapper<Register> wrapper=new QueryWrapper<>();
        wrapper.like("place","68606772");
        registerDao.selectList(wrapper);
    }
}
