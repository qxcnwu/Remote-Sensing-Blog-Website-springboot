package com.qxc.gacspboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.pojo.Cc;
import com.qxc.gacspboot.pojo.PicAnsMap;
import com.qxc.gacspboot.service.impl.PicDataServiceImpl;
import com.qxc.gacspboot.service.impl.SenServiceImpl;
import com.qxc.gacspboot.service.impl.SensorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DEController {
    @Autowired
    private PicDataServiceImpl picDataService;

    @Autowired
    private SensorServiceImpl sensorService;

    @Autowired
    private SenServiceImpl senService;

    @RequestMapping("/meta")
    public R getMeta(@RequestBody String mapList){
        List<PicAnsMap> resultList= JSONArray.parseArray( mapList, PicAnsMap.class);
        R r=new R();
        r.setData(picDataService.getListMap(resultList));
        r.setFinish(true);
        return r;
    }

    @RequestMapping("/cc")
    public R getCc(@RequestBody List<Cc> mapList){
        R r=new R();
        r.setData(sensorService.getAll(mapList));
        r.setFinish(true);
        return r;
    }

    @RequestMapping("/ccall")
    public R getCcAll(){
        R r=new R();
        r.setData(senService.getAll());
        r.setFinish(true);
        return r;
    }
}
