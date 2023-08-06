package com.qxc.gacspboot.Config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "picdata")
@PropertySource("classpath:AutoDataProp.properties")
@Data
@Slf4j
public class PicDataConfig {
    public Map<String, String> path=new HashMap<>();
    public Map<String,Integer> row=new HashMap<>();
    public Map<String,Integer> col=new HashMap<>();
}
