package com.qxc.gacspboot.dao.DE;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trans_Air {
    private int AtmModel;
    private int AeroStyle;
    private double VIS;
    private double CO2;
    private double H2O;
    private double O3;
    private double GrdAlt;
    private double wave1;
    private double wave2;
    private double wave3;
    private double SZN;
    private String chose;

    @Override
    public String toString() {
        return AtmModel + "\n" + AeroStyle + "\n" + VIS + "\n" + CO2 + "\n" + H2O + "\n" +
                O3 + "\n" + GrdAlt + "\n" + wave1 + "\n" + wave2 + "\n" + wave3 + "\n" + SZN;
    }
}
