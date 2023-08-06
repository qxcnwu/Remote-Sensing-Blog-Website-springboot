package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.Config.PicDataConfig;
import com.qxc.gacspboot.pojo.PicAnsMap;
import com.qxc.gacspboot.service.UTiles.FastReadPicData;
import com.qxc.gacspboot.service.picDataService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class  PicDataServiceImpl implements picDataService {
    @Autowired
    private PicDataConfig picDataConfig;

    /**
     * 获取文件路径
     * @param propName
     * @return
     */
    private @NotNull String path(String propName, int date){
        if(!picDataConfig.path.containsKey(propName)){
            log.warn("查询属性名失败！！");
            return "";
        }
        return picDataConfig.path.get(propName)+"/"+date;
    }

    public Integer id(int col,int row,String propName){
        if(!picDataConfig.col.containsKey(propName)){
            log.warn("查询属性名失败！！");
            return -1;
        }
        return picDataConfig.col.get(propName)*row+col;
    }

    /**
     * 对每个pic对象预处理
     */
    public void preProcess(@NotNull PicAnsMap picAnsMap){
        picAnsMap.getPropNames().forEach(name->{
            picAnsMap.getId().put(name,id(picAnsMap.getCol(),picAnsMap.getRow(),name));
        });
    }

    @Override
    public Float getOne(int id, int date, String propName) {
        try{
            return FastReadPicData.getAns(picDataConfig.getPath().get(propName)+date+".bin",id);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return -9999f;
        }
    }

    @Override
    public List<Float> getList(int id, @NotNull List<Integer> dates, String propNames) {
        List<Float> answer=new ArrayList<>();
        dates.parallelStream().forEachOrdered(date->{
            answer.add(getOne(id,date,propNames));
        });
        return answer;
    }

    @Override
    public void getMap(PicAnsMap picAnsMap) {
        preProcess(picAnsMap);
        AtomicReference<List<Float>> tempAnswer = new AtomicReference<>();
        picAnsMap.getPropNames().parallelStream().forEach(name->{
            tempAnswer.set(getList(picAnsMap.getId().get(name), picAnsMap.getDates(), name));
            picAnsMap.getAnswerMap().put(name,tempAnswer.get());
        });
    }

    @Override
    public List<PicAnsMap> getListMap(@NotNull List<PicAnsMap> mapList) {
        mapList.parallelStream().forEach(this::getMap);
        return mapList;
    }


}
