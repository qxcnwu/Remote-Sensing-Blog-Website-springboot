package com.qxc.gacspboot.UTiles;

import com.qxc.gacspboot.dao.AutoWrite;
import com.qxc.gacspboot.pojo.AnsMap;
import com.qxc.gacspboot.pojo.Sensor;
import com.qxc.gacspboot.service.impl.SenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ScanTools {
    public static void getAll(String path, List<String> list, String regex) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files==null){
            log.warn(path+"为空!");
            return;
        }
        for(File file:files){
            if(file.isDirectory()){
                getAll(file.getAbsolutePath(),list,regex);
            }
            else if(file.getName().matches(regex)){
                list.add(file.getAbsolutePath());
            }
        }
    }

    public static <T> void Add(@NotNull List<String> fileList, AutoWrite<T> autoWrite){
        BufferedReader bufferedReader=null;
        for(String path:fileList){
            String baseName=autoWrite.parseFileName(new File(path).getName());
            try{
                bufferedReader=new BufferedReader(new FileReader(path));
                String tempString;
                while ((tempString = bufferedReader.readLine()) != null) {
                    AnsMap<T> ansMap=autoWrite.parse(baseName,tempString);
                    autoWrite.addOne(ansMap.getKey(), ansMap.getValue());
                }
            }catch (FileNotFoundException ex){
                log.error(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try{
                    if(bufferedReader!=null) bufferedReader.close();
                }catch (IOException ex){
                    log.error(ex.getMessage());
                }
            }
        }
    }

    public static <T> void insertMain(@NotNull AutoWrite<T> autoWrite, @NotNull SenServiceImpl service){
        List<String> files=new ArrayList<>();
        getAll(autoWrite.getPath(), files, autoWrite.getRegex());
        Add(files,autoWrite);

        Set<String> sen=new HashSet<>();
        files.forEach(res->{
            try{
                String name=new File(res).getName();
                sen.add(name.substring(0,name.lastIndexOf('_')));
            }catch (Exception ex){
                log.error("file "+res+" 不能被解析");
            }
        });
        List<Sensor> sensors=new ArrayList<>();
        sen.forEach(res->{
            sensors.add(new Sensor(res));
        });
        service.updateName(sensors);
    }
}
