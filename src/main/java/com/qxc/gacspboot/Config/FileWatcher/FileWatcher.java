package com.qxc.gacspboot.Config.FileWatcher;

import com.qxc.gacspboot.UTiles.ScanTools;
import com.qxc.gacspboot.dao.AutoWrite;
import com.qxc.gacspboot.service.impl.SenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

@Slf4j
public class FileWatcher extends FileAlterationListenerAdaptor {
    private final AutoWrite<?> autoWrite;
    private final SenServiceImpl service;

    public FileWatcher(AutoWrite<?> autoWrite, SenServiceImpl service){
        this.autoWrite=autoWrite;
        this.service=service;
    }

    void runMain(){
        log.info(autoWrite.getPath()+" change");
        try{
            ScanTools.insertMain(autoWrite,service);
        }catch (Exception ex){
            log.warn(ex.getMessage());
        }
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    @Override
    public void onDirectoryCreate(File directory) {
        runMain();
    }

    @Override
    public void onDirectoryChange(File directory) {
        runMain();
    }

    @Override
    public void onDirectoryDelete(File directory) {
        runMain();
    }

    @Override
    public void onFileCreate(File file) {
        runMain();
    }

    @Override
    public void onFileChange(File file) {
        runMain();
    }

    @Override
    public void onFileDelete(File file) {
        runMain();
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
    }
}
