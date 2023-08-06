package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.pojo.Enclosure;
import com.qxc.gacspboot.pojo.EnclosureId;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.EnclosureService;
import com.qxc.gacspboot.service.EnclosureUploadService;
import com.qxc.gacspboot.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author qxc
 * @Date 2022 2022/8/18 9:20
 * @Version 1.0
 * @PACKAGE com.qxc.gacspboot.controller
 */
@RestController
@RequestMapping("/enclosure")
@Slf4j
public class enclosureController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private EnclosureService enclosureService;

    @Autowired
    private EnclosureUploadService enclosureUploadService;

    @AuthorNeed
    @RequestMapping("/getuploadid")
    public R getid(@CookieValue("token") String token) {
        Login login = redisService.SearchToken(token);
        R r = new R();
        EnclosureId enclosureId = new EnclosureId();
        enclosureId.setUserid(login.getUsername());
        final String newId = enclosureService.getNewId(enclosureId);
        r.setData(newId);
        return r;
    }

    @AuthorNeed
    @PostMapping("/uploadFile")
    public R upload(@CookieValue("token") String token, String fileid, MultipartFile image) {
        Login login = redisService.SearchToken(token);
        R r = new R();
        System.out.println("图像Image:" + image);
        // 首先上传文件
        String upFlag = enclosureUploadService.uploadFile(login.getUsername(), image);
        if (upFlag == null) {
            r.setData(false);
        }
        // 上传结果写入数据库
        Enclosure enclosure = new Enclosure();
        enclosure.setUserid(login.getUsername());
        enclosure.setPath(upFlag);
        enclosure.setFileid(fileid);
        r.setData(enclosureUploadService.uploadDatabase(enclosure));
        return r;
    }

    @AuthorNeed
    @RequestMapping("/getAllEnclosure")
    public R getClosure(@CookieValue("token") String token,String fileid) {
        R r = new R();
        final List<Enclosure> files = enclosureUploadService.getFiles(fileid);
        r.setData(files);
        return r;
    }

}
