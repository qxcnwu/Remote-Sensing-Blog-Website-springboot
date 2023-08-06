package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.dao.Anno.AutoData;
import com.qxc.gacspboot.dao.AutoWrite;
import com.qxc.gacspboot.dao.DE.sensor;
import com.qxc.gacspboot.pojo.AnsMap;
import com.qxc.gacspboot.pojo.Cc;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@ConditionalOnBean(RedisTemplate.class)
@PropertySource("classpath:AutoDataProp.properties")
@AutoData
public class SensorServiceImpl extends AutoWrite<sensor>{
    @Value("${sensor.path}")
    private String sourcePath;

    @Value("${sensor.regex}")
    private String regex;

    public SensorServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public AnsMap<sensor> parse(String aminName,@NotNull String statement) {
        AnsMap<sensor> ansMap=new AnsMap<>();
        String[] list=statement.split("\\s+");
        assert list.length==5;
        sensor sen=new sensor(Double.valueOf(list[1]),
                Double.valueOf(list[2]),
                Double.valueOf(list[3]),
                Double.valueOf(list[3]));
        ansMap.setKey(aminName+list[0]);
        ansMap.setValue(sen);
        return ansMap;
    }

    @Override
    public String parseFileName(@NotNull String fileName) {
        int a=fileName.lastIndexOf("_");
        if(a==-1) return fileName;
        return fileName.substring(0,a);
    }

    @Override
    public String getPath(){
        return this.sourcePath;
    }

    @Override
    public String getRegex(){
        return this.regex;
    }

    private List<sensor> getOne(String propName, @NotNull List<String> date){
        List<String> ans=new ArrayList<>();
        date.parallelStream().forEachOrdered(d->{
            ans.add(propName+d);
        });
        return this.selectList(ans);
    }

    public void getList(@NotNull Cc cc){
        cc.getName().parallelStream().forEach(name->{
            cc.getMap().put(name,getOne(name,cc.getDate()));
        });
    }

    public List<Cc> getAll(@NotNull List<Cc> ccList){
        ccList.parallelStream().forEach(this::getList);
        return ccList;
    }
}
