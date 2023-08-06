package com.qxc.gacspboot.dao.DE;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class sensor implements Serializable {
    private double r;
    private double g;
    private double b;
    private double nir;

    @Contract(pure = true)
    public sensor(Double valueOf, Double valueOf1, Double valueOf2, Double valueOf3) {
        this.r=valueOf;
        this.g=valueOf1;
        this.b=valueOf2;
        this.nir=valueOf3;
    }
}
