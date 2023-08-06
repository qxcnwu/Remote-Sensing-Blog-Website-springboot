package com.qxc.gacspboot.service;

import com.qxc.gacspboot.pojo.PicAnsMap;

import java.util.List;

public interface picDataService {
    Float getOne(int id,int date,String propName);
    List<Float> getList(int id,List<Integer> dates,String propNames);
    void getMap(PicAnsMap picAnsMap);
    List<PicAnsMap> getListMap(List<PicAnsMap> mapList);
}
