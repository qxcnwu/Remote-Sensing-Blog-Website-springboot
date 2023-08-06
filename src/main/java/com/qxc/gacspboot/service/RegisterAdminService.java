package com.qxc.gacspboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.RegisterAdmin;

public interface RegisterAdminService extends IService<RegisterAdmin> {
    boolean Login(Login login);
}
