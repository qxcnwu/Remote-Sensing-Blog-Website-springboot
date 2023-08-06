package com.qxc.gacspboot.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 邱星晨
 */
@Configuration
@ConfigurationProperties(prefix = "upload")
@PropertySource("classpath:AutoDataProp.properties")
@Data
public class FileUpLoadConfig {
    public String dir;
    public String enclosuredir;
    public String aoddir;
    public String waterdir;
}
