package com.qxc.gacspboot.dao;

import com.qxc.gacspboot.pojo.AnsMap;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public abstract class AutoWrite<T> {
    private RedisTemplate<String,Object> redisTemplate;
    private String path;
    private String regex;

    @Contract(pure = true)
    public AutoWrite(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public T selectOne(String keyName){
        return (T) redisTemplate.opsForValue().get(keyName);
    }

    public List<T> selectList(List<String> arrayList){
        return (List<T>) redisTemplate.opsForValue().multiGet(arrayList);
    }

    public List<T> selectRange(String keyName,int start,int end){
        List<String> keyList=new ArrayList<>();
        for(int i=start;i<=end;i++){
            keyList.add(keyName+i);
        }
        return selectList(keyList);
    }

    public void updateOne(String key,T value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void updateList(Map<String,T> map){
        redisTemplate.opsForValue().multiSet(map);
    }

    public void updateRange(String keyName, int start, @NotNull List<T> list){
        Map<String,T> map=new HashMap<>();
        AtomicInteger i= new AtomicInteger(start);
        list.forEach(t->{
            map.put(keyName+i,t);
            i.getAndIncrement();
        });
        updateList(map);
    }

    public void addOne(String key,T value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void addOne(Map<String,T> map){
        updateList(map);
    }

    public void addList(Map<String,T> map){
        updateList(map);
    }

    public void addListRange(String keyName, int start, @NotNull List<T> list){
        updateRange(keyName,start,list);
    }

    public <T> T deleteOne(String key){
        return (T) redisTemplate.delete(key);
    }

    public <T> Long deleteList(List<String> list){
        return redisTemplate.delete(list);
    }

    public <T> Long deleteRange(String mainName,int start,int end){
        List<String> list=new ArrayList<>();
        for(int i=start;i<=end;i++){
            list.add(mainName+i);
        }
        return deleteList(list);
    }

    public abstract  <T> AnsMap<T> parse(String mainName, String statement);

    public abstract String parseFileName(String fileName);
}
