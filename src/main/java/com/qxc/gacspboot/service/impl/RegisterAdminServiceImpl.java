package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.dao.RegisterAdminDao;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.RegisterAdmin;
import com.qxc.gacspboot.service.RegisterAdminService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterAdminServiceImpl extends ServiceImpl<RegisterAdminDao, RegisterAdmin> implements RegisterAdminService {
    @Autowired
    private RegisterAdminDao registerAdminDao;

    @Override
    public boolean Login(@NotNull Login login) {
        LambdaQueryWrapper<RegisterAdmin> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(RegisterAdmin::getUsername,login.getUsername());
        wrapper.eq(RegisterAdmin::getPassword,login.getPassword());
        return registerAdminDao.selectCount(wrapper)>0;
    }
}
