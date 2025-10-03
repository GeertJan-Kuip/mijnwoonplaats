package com.geertjankuip.mijnwoonplaats.textcreation;

public enum NationalData2023 {

    PERCENTAGE65PLUS(20.2), PERCENTAGE15MIN(15.3);

    private final Double percentage;

    NationalData2023(Double percentage) {
        this.percentage = percentage;
    }

    public Double getValue(){
        return this.percentage;
    }
}
