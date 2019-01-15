package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.Queue;
import com.example.yy.algorithm_lab.collections.Stack;

public class parkingLot {
    private Stack<Car> parking;
    private Stack<Car> out;
    private Queue<Car> wait;
    private double price = 5;

    public parkingLot(Stack<Car> parking, Stack<Car> out, Queue<Car> wait) {
        this.parking = parking;
        this.out = out;
        this.wait = wait;
    }

    public double calculate(Car car) {
        return price * (car.getLeave_time() - car.getAt_time());
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Stack<Car> getParking() {
        return parking;
    }

    public void setParking(Stack<Car> parking) {
        this.parking = parking;
    }

    public Stack<Car> getOut() {
        return out;
    }

    public void setOut(Stack<Car> out) {
        this.out = out;
    }

    public Queue<Car> getWait() {
        return wait;
    }

    public void setWait(Queue<Car> wait) {
        this.wait = wait;
    }
}
