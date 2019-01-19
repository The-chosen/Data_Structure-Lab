package com.example.yy.algorithm_lab.db;

import com.example.yy.algorithm_lab.db.Site;

import org.litepal.crud.DataSupport;

public class DiEdge extends DataSupport {
    private Site from;
    private Site to;
    private double weight;

    public DiEdge() {}

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

    public Site getFrom() {
        return from;
    }

    public void setFrom(Site from) {
        this.from = from;
    }

    public Site getTo() {
        return to;
    }

    public void setTo(Site to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", from, to, weight);
    }
}
