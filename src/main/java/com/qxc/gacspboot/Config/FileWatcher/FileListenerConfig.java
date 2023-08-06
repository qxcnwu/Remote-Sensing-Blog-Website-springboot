package com.qxc.gacspboot.Config.FileWatcher;

import com.qxc.gacspboot.dao.AutoWrite;
import com.qxc.gacspboot.service.impl.SenServiceImpl;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class FileListenerConfig {

    @Contract("_ -> new")
    public static @NotNull FileAlterationMonitor getMonitor(@NotNull AutoWrite<?> autoWrite, SenServiceImpl service){
        FileAlterationObserver observer = new FileAlterationObserver(autoWrite.getPath());
        observer.addListener(new FileWatcher(autoWrite,service));
        return new FileAlterationMonitor(TimeUnit.SECONDS.toMillis(1), observer);
    }
}
