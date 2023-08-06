package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.Config.TransSavConfig;
import com.qxc.gacspboot.dao.DE.Trans_Air;
import com.qxc.gacspboot.service.TransSavService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TransSavServiceImpl implements TransSavService {

    @Autowired
    private TransSavConfig transSavConfig;

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public BufferedImage TransPlot(HttpServletRequest request, HttpServletResponse response, Trans_Air trans_air) {
        return null;
    }

    @Override
    public BufferedImage SensorTransAir(HttpServletRequest request, HttpServletResponse response, Trans_Air trans_air) {
        return null;
    }

    @Override
    public BufferedImage SensorRSRAir(HttpServletRequest request, HttpServletResponse response, Trans_Air trans_air) {
        return null;
    }

    @Override
    public boolean TransAir(Trans_Air trans_air) {
        return false;
    }

    @Override
    public void reAnswer(BufferedImage bufferedImage, @NotNull HttpServletResponse response) {

    }
}
