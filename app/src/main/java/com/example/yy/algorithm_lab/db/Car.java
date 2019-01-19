package com.example.yy.algorithm_lab.db;

import org.litepal.crud.DataSupport;

import java.util.Calendar;

public class Car extends DataSupport {
    private int number;
    private Calendar at_time;
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

    public Calendar getAt_time() {
        return at_time;
    }

    public void setAt_time(Calendar at_time) {
        this.at_time = at_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
