package com.qxc.gacspboot.service;

import com.qxc.gacspboot.pojo.EnclosureId;

/**
 * @Author qxc
 * @Date 2022 2022/8/18 9:36
 * @Version 1.0
 * @PACKAGE com.qxc.gacspboot.service
 */
public interface EnclosureService {
    /**
     * 添加附件id
     * @return
     */
    String getNewId(EnclosureId enclosureId);
}
