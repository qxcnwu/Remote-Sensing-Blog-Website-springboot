package com.qxc.gacspboot.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "download")
@PropertySource("classpath:AutoDataProp.properties")
@Data
public class DownloadConfig {
    public String rootDir;
    public Map<String,String> map;
}
