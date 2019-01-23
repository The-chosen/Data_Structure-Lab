package com.example.yy.algorithm_lab.Android.db;

import org.litepal.crud.LitePalSupport;

import java.util.Calendar;

/**
 * @author YangYue
 * @description 停车的车子的类
 * @date 2019-2-22 10:00
 */

public class Car extends LitePalSupport {
    private int number;
    private String at_time;
    private String state; //只有两种状态，p(parking)或w(waiting)

    public Car(int number) {
        this.number = number;
    }

    public Car() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAt_time(String at_time) {
        this.at_time = at_time;
    }

    public String getAt_time() {
        return at_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
