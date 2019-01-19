package com.example.yy.algorithm_lab.db;

import android.support.annotation.NonNull;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public class Site extends DataSupport implements Serializable, Comparable<Site>{
    private int id;
    private String name;
    private String intro;
    private int popul;
    private boolean hasBreak;
    private boolean hasWC;
    private List<DiEdge> diEdges;

    public Site() {}

    public Site(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Site(int id, String name, String intro, boolean hasBreak, boolean hasWC) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.popul = popul;
        this.hasBreak = hasBreak;
        this.hasWC = hasWC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPopul() {
        return popul;
    }

    public void setPopul(int popul) {
        this.popul = popul;
    }

    public boolean isHasBreak() {
        return hasBreak;
    }

    public void setHasBreak(boolean hasBreak) {
        this.hasBreak = hasBreak;
    }

    public boolean isHasWC() {
        return hasWC;
    }

    public void setHasWC(boolean hasWC) {
        this.hasWC = hasWC;
    }

    public List<DiEdge> getDiEdges() {
        return diEdges;
    }

    public void setDiEdges(List<DiEdge> diEdges) {
        this.diEdges = diEdges;
    }

    @Override
    public int compareTo(@NonNull Site o) {
        if (this.getPopul() < o.getPopul()) {
            return -1;
        }
        else if (this.getPopul() < o.getPopul()) {
            return 1;
        }
        else return 0;
    }
}
