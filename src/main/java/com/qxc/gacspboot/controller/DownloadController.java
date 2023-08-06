package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.Config.DownloadConfig;
import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.service.impl.DownFileServiceImpl;
import com.qxc.gacspboot.service.impl.DownLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/download")
@Slf4j
public class DownloadController {
    @Autowired
    private DownloadConfig downloadConfig;
    @Autowired
    private DownLoadServiceImpl downLoadService;
    @Autowired
    private DownFileServiceImpl downFileService;

    @RequestMapping("/file/{id}")
    public void downLoad(@NotNull HttpServletResponse response, @PathVariable String id){
        String path=downFileService.getFile(id);
        if(path.equals("")) return;
        downLoadService.download(path,response);
    }

    @AuthorNeed
    @RequestMapping("/artical/{id}")
    public void downloadArtical(@NotNull HttpServletResponse response, @PathVariable String id){
        String path=downFileService.getArtical(id);
        if(path.equals("")) return;
        downLoadService.download(path,response);
    }

    @RequestMapping("/other/{id}")
    public void downloadOther(@NotNull HttpServletResponse response, @PathVariable String id){
        String path=downFileService.getOther(id);
        if(path.equals("")) return;
        downLoadService.download(path,response);
    }

    @RequestMapping("/all")
    public R downAll(){
        R r=new R();
        r.setData(downFileService.getAll());
        r.setFinish(true);
        return r;
    }
}
