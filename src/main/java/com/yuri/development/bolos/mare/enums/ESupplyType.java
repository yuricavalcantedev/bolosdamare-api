package com.yuri.development.bolos.mare.enums;

public enum ESupplyType {

    ML ("ml"),
    G ("gramas");

    private String description;

    private ESupplyType(String description){
        this.description = description;
    }
}
