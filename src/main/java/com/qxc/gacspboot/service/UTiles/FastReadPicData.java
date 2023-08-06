package com.qxc.gacspboot.service.UTiles;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@Slf4j
public class FastReadPicData {

    /**
     * 获取值
     * @param arr
     * @param index
     * @return
     */
    @Contract(pure = true)
    public static int getInt(byte @NotNull [] arr, int index) {

        return 	(0xff000000 & (arr[index+3] << 24))  |
                (0x00ff0000 & (arr[index+2] << 16))  |
                (0x0000ff00 & (arr[index+1] << 8))   |
                (0x000000ff &  arr[index]);
    }

    public static float getFloat(byte[] arr, int index) {
        return Float.intBitsToFloat(getInt(arr, index));
    }

    /**
     * 反序列化时间
     * @param s
     * @return
     */
    public static @NotNull String dateGet(@NotNull String s){
        /*
         * 输入字符串格式 xxxx:xx-xx xxxx:x-x
         */
        String[] uList={"00","0",""};
        String[] strList=s.split("-");
        String year=strList[0];
        int month=Integer.parseInt(strList[1]);
        int day=Integer.parseInt(strList[2]);
        boolean isRum=Integer.parseInt(year)%4==0;
        int[] runList={31,29,31,30,31,30,31,31,30,31,30,31};
        int[] list={31,28,31,30,31,30,31,31,30,31,30,31};
        int num=0;
        if(isRum){
            for(int i=1;i<month;i++){
                num+=runList[i-1];
            }
        }
        else{
            for(int i=1;i<month;i++){
                num+=list[i-1];
            }
        }
        num+=day;
        return year+uList[(int)Math.log10(num)]+num;
    }

    /**
     * 快速读取图像值
     * @param path
     * @param index
     * @return
     * @throws IOException
     */
    private static float read(String path,String index){
        try{
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            int temp=Integer.parseInt(index);
            raf.seek(temp* 4L);
            byte[] buff=new byte[4];
            raf.read(buff);
            raf.close();
            return getFloat(buff,0);
        }catch (IOException ex){
            log.warn(index+" not found!!");
            return -9999f;
        }
    }

    /**
     * 读取方案
     * @param dir
     * @param date
     * @return
     */
    public static float getAns(String path, Integer date) {
        float wvp=-9999f;
        File fileWvp=new File(path);
        if(fileWvp.exists()){
            wvp=read(path, String.valueOf(date));
        }
        return wvp;
    }
}
