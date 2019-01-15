package com.example.yy.algorithm_lab.sys;

public class Car {
    private int number;
    private int at_time;
    private int leave_time;

    public Car(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAt_time() {
        return at_time;
    }

    public void setAt_time(int at_time) {
        this.at_time = at_time;
    }

    public int getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(int leave_time) {
        this.leave_time = leave_time;
    }
}
