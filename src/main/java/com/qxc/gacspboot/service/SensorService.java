package com.qxc.gacspboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxc.gacspboot.pojo.Sensor;

import java.util.List;

public interface SensorService extends IService<Sensor> {
    void updateName(List<Sensor> fileList);
    List<Sensor> getAll();
}
