package com.example.yy.algorithm_lab.db;

import com.example.yy.algorithm_lab.db.Site;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class DiEdge extends LitePalSupport implements Serializable {
    private Site from;
    private Site to;
    private String fromName;
    private String toName;
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

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    @Override
    public String toString() {
        return String.format("%s->%s %.2f", from.getName(), to.getName(), weight);
    }
}
