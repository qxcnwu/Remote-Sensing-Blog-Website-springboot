package com.qxc.gacspboot.service;

import com.qxc.gacspboot.pojo.Enclosure;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author qxc
 * @Date 2022 2022/8/18 10:11
 * @Version 1.0
 * @PACKAGE com.qxc.gacspboot.service
 */
public interface EnclosureUploadService {
    /**
     * 上传文件
     *
     * @param id
     * @param file
     * @return
     */
    String uploadFile(String id, MultipartFile file);

    /**
     * 更新数据库
     */
    boolean uploadDatabase(Enclosure enclosure);


    List<Enclosure> getFiles(String fileId);

    String getPath(int id);
}
