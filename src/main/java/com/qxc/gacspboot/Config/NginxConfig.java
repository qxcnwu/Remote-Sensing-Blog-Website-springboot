package com.qxc.gacspboot.Config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 邱星晨
 */
@Configuration
@ConfigurationProperties(prefix = "nginx")
@PropertySource("classpath:AutoDataProp.properties")
@Data
@Slf4j
public class NginxConfig {
    private String ip;
    private int port;
}
