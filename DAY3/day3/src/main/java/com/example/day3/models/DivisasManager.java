package com.example.day3.models;

public class DivisasManager {
    private Float valueOrigin;
    private Float valurConverter;

    //Constructor
    public DivisasManager(Float valueOrigin, Float valurConverter){
        this.valueOrigin = valueOrigin;
        this.valurConverter = valurConverter;
    }

    public Float getValueOrigin() {
        return valueOrigin;
    }

    public void setValueOrigin(Float valueOrigin) {
        this.valueOrigin = valueOrigin;
    }

    public Float getValurConverter() {
        return valurConverter;
    }

    public void setValurConverter(Float valurConverter) {
        this.valurConverter = valurConverter;
    }
}
