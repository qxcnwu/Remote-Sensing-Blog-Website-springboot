package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.Config.FileUpLoadConfig;
import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.service.impl.DownLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/picture")
@Slf4j
public class PicShowController {
    @Autowired
    private FileUpLoadConfig fileUpLoadConfig;
    @Autowired
    private DownLoadServiceImpl downLoadService;

    @RequestMapping(value="/show/{id}")
    public void getPic(@PathVariable String id, HttpServletResponse response){
        String path= fileUpLoadConfig.getDir()+id;
        downLoadService.download(path,response);
    }

    @AuthorNeed
    @RequestMapping(value="/getEnclosure/{id}")
    public void getEnclosure(@PathVariable int id, HttpServletResponse response){
        downLoadService.downloadEn(id,response);
    }

    @RequestMapping(value="/aod/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getAod(@PathVariable String id, HttpServletResponse response){
        String path= fileUpLoadConfig.getAoddir()+id;
        byte[] pic=downLoadService.downloadPic(path);
        HttpHeaders headers=new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(pic,headers, HttpStatus.OK);
    }

    @RequestMapping(value="/water/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getWater(@PathVariable String id){
        String path= fileUpLoadConfig.getWaterdir()+id;
        byte[] pic=downLoadService.downloadPic(path);
        HttpHeaders headers=new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(pic,headers, HttpStatus.OK);
    }
}
