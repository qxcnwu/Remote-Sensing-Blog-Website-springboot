package com.qxc.gacspboot.Config;

import com.qxc.gacspboot.Config.FileWatcher.FileListenerConfig;
import com.qxc.gacspboot.UTiles.ScanTools;
import com.qxc.gacspboot.dao.Anno.AutoData;
import com.qxc.gacspboot.dao.AutoWrite;
import com.qxc.gacspboot.service.impl.SenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

@Slf4j
@Configuration
public class AutoWriteConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private SenServiceImpl service;

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        ApplicationContext applicationContext= event.getApplicationContext();
        Map<String,Object> stringObjectMap=applicationContext.getBeansWithAnnotation(AutoData.class);
        for(String s: stringObjectMap.keySet()){
            Class<?> clazz=stringObjectMap.get(s).getClass();
            if(AutoWrite.class.isAssignableFrom(clazz)){
                AutoWrite<?> autoWrite= (AutoWrite<?>) stringObjectMap.get(s);
                ScanTools.insertMain(autoWrite,service);
                log.info(clazz.getName()+"写入Redis");
                if(stringObjectMap.get(s).getClass().getAnnotation(AutoData.class).value()){
                    FileAlterationMonitor fileAlterationMonitor= FileListenerConfig.getMonitor(autoWrite,service);
                    try {
                        fileAlterationMonitor.start();
                        log.info(autoWrite.getPath()+"监测启动");
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                }
            }
            else{
                log.error("{} such class not implements IAutoDataRedis Interface",clazz.getName());
            }
        }
    }
}
