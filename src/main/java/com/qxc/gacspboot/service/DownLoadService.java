package com.qxc.gacspboot.service;

import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletResponse;

public interface DownLoadService {
    /**
     * @param path
     * @param response
     */
    void download(String path, HttpServletResponse response);

    void downloadEn(int id, HttpServletResponse response);

    /**
     * @param path
     * @return
     */
    byte[] downloadPic(String path);

}
