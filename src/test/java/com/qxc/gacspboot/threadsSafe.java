package com.qxc.gacspboot;

import org.jetbrains.annotations.Contract;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadsSafe {

    private static final Map<Integer, Integer> map = new ConcurrentHashMap<>();
    private static final List<Integer> list=Collections.synchronizedList(new ArrayList<>());

    @Contract(pure = true)
    public static void delcur(){
        while(list.size()!=0){
            for(int i:list){
                System.out.println(i+"执行删除");
                list.remove(i);
            }
        }
        System.out.println("删除线程完毕");
    }

    @Contract(pure = true)
    public static void main(String[] args) {
        List<Integer> array=new ArrayList<>();
        for(int i=0;i<100;i++){
            array.add(i);
        }
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i=0;i<=100;i++) {
            int finalI = i;
            executor.submit(()->{
                list.add(-1* finalI);
                map.put(-1* finalI,-1* finalI);
            });
        }
        executor.submit(threadsSafe::delcur);

        for (Integer integer : array) {
            executor.submit(()->{
                list.add(integer+1);
                map.put(integer,integer);
                System.out.println("添加");
            });
        }


        executor.shutdown();
        System.out.println(list.size());
    }
}
