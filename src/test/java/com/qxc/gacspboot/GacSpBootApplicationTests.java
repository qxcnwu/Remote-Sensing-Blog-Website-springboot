package com.qxc.gacspboot;

import com.qxc.gacspboot.Config.DownloadConfig;
import com.qxc.gacspboot.Config.PicDataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GacSpBootApplicationTests {

    @Autowired
    private PicDataConfig picDataService;

    @Autowired
    private DownloadConfig downloadConfig;

    @Test
    void contextLoads() {
        System.out.println(picDataService);
        System.out.println(downloadConfig);
    }

}
