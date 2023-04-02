package com.yuri.development.bolos.mare.enums;

public enum EOrderStatus {

    CREATED ("Order created!"),
    PROCESSING ("Order is processing!"),
    DONE("Order is ready to be eaten!"),
    CANCELLED ("Order was cancelled :) ");

    private String description;

    EOrderStatus(String description){
        this.description = description;
    }
}
