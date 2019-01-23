package com.example.yy.algorithm_lab.Android.util;

/**
 * @author YangYue
 * @description 每个景点在listView中的显示
 * @date 2019-2-22 10:00
 */

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
