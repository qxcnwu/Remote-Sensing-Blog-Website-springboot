package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.dao.DownFileDao;
import com.qxc.gacspboot.pojo.DownFile;
import com.qxc.gacspboot.service.DownFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DownFileServiceImpl extends ServiceImpl<DownFileDao, DownFile> implements DownFileService {
    @Autowired
    private DownFileDao downFileDao;

    public String get(String id){
        LambdaQueryWrapper<DownFile> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(DownFile::getId,id);
        DownFile downFile=downFileDao.selectOne(wrapper);
        if(downFile==null){
            log.info("文件不存在"+id);
            return "";
        }
        return downFile.getFilename();
    }

    @Override
    public String getArtical(String id) {
        return get(id);
    }

    @Override
    public String getFile(String id) {
        return get(id);
    }

    @Override
    public String getOther(String id) {
        return get(id);
    }

    @Override
    public List<List<DownFile>> getAll() {
        List<List<DownFile>> answer = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            LambdaQueryWrapper<DownFile> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DownFile::getRole, i);
            List<DownFile> temp = downFileDao.selectList(wrapper);
            temp.forEach(r -> r.setFilename(new File(r.getFilename()).getName()));
            answer.add(temp);
        }
        return answer;
    }
}
