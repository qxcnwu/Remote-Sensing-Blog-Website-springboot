package com.qxc.gacspboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxc.gacspboot.pojo.DownFile;

import java.util.List;

public interface DownFileService extends IService<DownFile> {
    String getArtical(String id);
    String getFile(String id);
    String getOther(String id);
    List<List<DownFile>> getAll();
}
