package com.yuri.development.bolos.mare.enums;

public enum ESupplyType {

    ML ("ml"),
    L ("litros"),
    G ("gramas"),
    KG ("quilos");

    private String description;

    private ESupplyType(String description){
        this.description = description;
    }
}
