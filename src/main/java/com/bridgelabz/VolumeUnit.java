package com.bridgelabz;

public enum VolumeUnit implements IMeasurable{
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factorToLitre;

    VolumeUnit(double factorToLitre){
        this.factorToLitre = factorToLitre;
    }

    @Override
    public double convertToBaseUnit(double value){
        return value * factorToLitre;
    }

    @Override
    public double convertFromBaseUnit(double baseValue){
        return baseValue / factorToLitre;
    }


    @Override
    public double getConversionFactor(){
        return factorToLitre;
    }

    @Override
    public String getUnitName(){
        return name();
    }
}