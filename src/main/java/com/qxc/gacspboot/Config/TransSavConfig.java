package com.qxc.gacspboot.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "trans-sav")
@PropertySource("classpath:AutoDataProp.properties")
@Data
public class TransSavConfig {
    private Map<String,String> dir;
    private Map<String,String> sav;
    private Map<String,String> input;
    private Map<String,String> output;
}
