package com.yuri.development.bolos.mare.util;

public enum ErrorConstants {

    RESOURCE_NOT_FOUND(1000, "Resource was not found using the id provided."),
    ITEM_ALREADY_EXISTS(1001, "This item already exists. If you wanna change its values, try to update the existing one"),

    PRODUCT_ALREADY_EXISTS(1002, "This product already exists. If you wanna change its values, try to update the existing one");

    private Integer errorCode;
    private String errorDescription;

    ErrorConstants(Integer errorCode, String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
