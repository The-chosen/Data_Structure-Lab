package com.example.yy.algorithm_lab.sys;

import com.example.yy.algorithm_lab.collections.Queue;
import com.example.yy.algorithm_lab.collections.Stack;
import com.example.yy.algorithm_lab.db.Car;

public class parkingLot {
    public final static int MAX_SIZE = 20;
    public final static double PRICE = 5;
    private Stack<Car> parking;
    private Stack<Car> out;
    private Queue<Car> wait;


    public parkingLot(Stack<Car> parking, Stack<Car> out, Queue<Car> wait) {
        this.parking = parking;
        this.out = out;
        this.wait = wait;
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
