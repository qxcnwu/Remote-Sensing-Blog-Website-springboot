package com.qxc.gacspboot.Config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpConfig {
    @Bean
    public PaginationInterceptor PaginationInterceptor(){
        return new PaginationInterceptor();
    }
}
