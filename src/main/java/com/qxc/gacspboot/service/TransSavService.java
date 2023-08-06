package com.qxc.gacspboot.service;

import com.qxc.gacspboot.dao.DE.Trans_Air;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

public interface TransSavService {
    BufferedImage TransPlot(HttpServletRequest request, HttpServletResponse response, Trans_Air trans_air);
    BufferedImage SensorTransAir(HttpServletRequest request, HttpServletResponse response, Trans_Air trans_air);
    BufferedImage SensorRSRAir(HttpServletRequest request, HttpServletResponse response, Trans_Air trans_air);
    boolean TransAir(Trans_Air trans_air);
    void reAnswer(BufferedImage bufferedImage, @NotNull HttpServletResponse response);
}
