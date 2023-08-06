package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.Config.FileUpLoadConfig;
import com.qxc.gacspboot.dao.EnclosureFileDao;
import com.qxc.gacspboot.pojo.Enclosure;
import com.qxc.gacspboot.service.EnclosureUploadService;
import com.qxc.gacspboot.service.UTiles.FileDirCheck;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author qxc
 * @Date 2022 2022/8/18 10:21
 * @Version 1.0
 * @PACKAGE com.qxc.gacspboot.service.impl
 */
@Service
public class EnclosureUploadServiceImpl extends ServiceImpl<EnclosureFileDao, Enclosure> implements EnclosureUploadService {
    @Autowired
    private EnclosureFileDao enclosureFileDao;
    @Autowired
    private FileUpLoadConfig fileUpLoadConfig;

    @Override
    public String uploadFile(String userid, @NotNull MultipartFile fileUpload) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        assert fileName != null;
        fileName= UUID.randomUUID()+"_"+fileName;
        System.out.println(fileName);
        final String filePath = FileDirCheck.mkdir(fileUpLoadConfig.getEnclosuredir(), userid);

        //私人文件夹创建失败直接返回
        if (filePath == null) {
            return null;
        }
        try {
            final File file = new File(filePath + File.separator + fileName);
            fileUpload.transferTo(file);
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("upload failed" + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean uploadDatabase(Enclosure enclosure) {
        return enclosureFileDao.insert(enclosure) > 0;
    }

    @Override
    public List<Enclosure> getFiles(String fileId) {
        LambdaQueryWrapper<Enclosure> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Enclosure::getFileid, fileId);
        return new ArrayList<>(enclosureFileDao.selectList(lambdaQueryWrapper));
    }

    @Override
    public String getPath(int id) {
        LambdaQueryWrapper<Enclosure> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Enclosure::getId,id);
        final Enclosure enclosure = enclosureFileDao.selectOne(lambdaQueryWrapper);
        return enclosure.getPath();
    }
}
