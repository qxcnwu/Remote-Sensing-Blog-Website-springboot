package com.qxc.gacspboot.service.UTiles;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author qxc
 * @Date 2022 2022/8/18 10:35
 * @Version 1.0
 * @PACKAGE com.qxc.gacspboot.service.UTiles
 */
public class FileDirCheck {
    public static String mkdir(String basedir,String name){
        final Path path1 = Paths.get(basedir, name);
        final File file = path1.toFile();
        if(file.exists()){
            return file.getAbsolutePath();
        }else{
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            }else{
                return null;
            }
        }
    }
}
