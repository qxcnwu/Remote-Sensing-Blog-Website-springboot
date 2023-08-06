package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.dao.EnclosureDao;
import com.qxc.gacspboot.pojo.EnclosureId;
import com.qxc.gacspboot.service.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author qxc
 * @Date 2022 2022/8/18 9:37
 * @Version 1.0
 * @PACKAGE com.qxc.gacspboot.service.impl
 */
@Service
public class EnclosureServiceImpl extends ServiceImpl<EnclosureDao, EnclosureId> implements EnclosureService {
    @Autowired
    private EnclosureDao enclosureDao;

    @Override
    public String getNewId(EnclosureId enclosureId) {
        final UUID uuid = UUID.randomUUID();
        enclosureId.setUuid(String.valueOf(uuid));
        enclosureDao.insert(enclosureId);
        return String.valueOf(uuid);
    }
}
