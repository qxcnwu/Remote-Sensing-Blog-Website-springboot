package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.dao.SensorDao;
import com.qxc.gacspboot.pojo.Sensor;
import com.qxc.gacspboot.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SenServiceImpl extends ServiceImpl<SensorDao, Sensor> implements SensorService {
    @Autowired
    private SensorDao sensorDao;

    @Override
    public void updateName(@NotNull List<Sensor> fileList) {
        List<Sensor> existList=sensorDao.selectList(null);
        fileList.forEach(res->{
            if(!existList.contains(res)){
                sensorDao.insert(res);
            }
        });
        existList.forEach(res->{
            if(!fileList.contains(res)){
                LambdaQueryWrapper<Sensor> wrapper=new LambdaQueryWrapper<>();
                wrapper.eq(Sensor::getSensorname,res.getSensorname());
                sensorDao.delete(wrapper);
            }
        });
        log.info("传感器列表更新完成");
    }

    @Override
    public List<Sensor> getAll() {
        return sensorDao.selectList(null);
    }
}
