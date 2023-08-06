package com.qxc.gacspboot.dao.DE;

import lombok.Data;
import org.jetbrains.annotations.Contract;

@Data
public class Trans_Plot {
    private final Trans_Air trans_air;

    @Contract(pure = true)
    public Trans_Plot(Trans_Air trans_air){
        this.trans_air=trans_air;
    }

    @Override
    public String toString() {
        int number=trans_air.getChose().split(" ").length;
        return number+"\n"+trans_air.getChose();
    }
}
