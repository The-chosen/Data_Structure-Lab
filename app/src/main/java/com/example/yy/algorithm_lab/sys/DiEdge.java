package com.example.yy.algorithm_lab.sys;

public class DiEdge {


    private Site v;
    private Site w;
    private double weight;

    public DiEdge(Site v, Site w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public Site from() {
        return v;
    }

    public Site to() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
