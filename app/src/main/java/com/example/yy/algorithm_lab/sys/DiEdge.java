package com.example.yy.algorithm_lab.sys;

public class DiEdge {
    private Site from;
    private Site to;
    private double weight;

    public DiEdge(Site from, Site to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public Site from() {
        return from;
    }

    public Site to() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", from, to, weight);
    }
}
