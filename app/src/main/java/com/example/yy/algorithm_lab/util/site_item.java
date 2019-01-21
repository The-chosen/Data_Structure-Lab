package com.example.yy.algorithm_lab.util;

public class site_item {
    private String number;
    private String name;
    private String intro;

    public site_item(String number, String name, String intro) {
        this.number = number;
        this.name = name;
        this.intro = intro;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
