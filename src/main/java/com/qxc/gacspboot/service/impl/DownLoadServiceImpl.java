package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.service.DownLoadService;
import com.qxc.gacspboot.service.EnclosureUploadService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class DownLoadServiceImpl implements DownLoadService {

    @Autowired
    private EnclosureUploadService enclosureUploadService;

    @Override
    public void download(String path, @NotNull HttpServletResponse response) {
        try{
            InputStream inputStream = new FileInputStream(path);
            response.reset();
            response.setContentType("application/octet-stream");
            String filename = new File(path).getName();
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            inputStream.close();
        }catch (Exception ex){
            log.error("No such file in download dir! "+path);
        }
    }

    @Override
    public void downloadEn(int id, HttpServletResponse response) {
        final String path = enclosureUploadService.getPath(id);
        download(path,response);
    }

    @Override
    public byte[] downloadPic(String path) {
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage bi;
            bi = ImageIO.read(new File(path));
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return bytes;
    }

}
