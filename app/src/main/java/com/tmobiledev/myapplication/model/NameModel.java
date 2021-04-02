package com.tmobiledev.myapplication.model;

public class NameModel {
    private String name;
    private Boolean isCheck;

    public NameModel(String name, Boolean isCheck){
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }
}
