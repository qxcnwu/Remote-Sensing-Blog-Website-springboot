package com.qxc.gacspboot.dao.DE;

import lombok.Data;
import org.jetbrains.annotations.Contract;

@Data
public class SensorRSR_Air {
    private final Trans_Air trans_air;

    @Contract(pure = true)
    public SensorRSR_Air(Trans_Air trans_air){
        this.trans_air=trans_air;
    }

    @Override
    public String toString() {
        return trans_air.getWave1()+" "+trans_air.getWave2()+" "+trans_air.getWave3();
    }
}
