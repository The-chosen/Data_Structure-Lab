package com.example.yy.algorithm_lab.sys;

import java.io.Serializable;

public class Site implements Serializable {
    private int id;
    private String name;
    private String intro;
    private int popul;
    private boolean hasBreak;
    private boolean hasWC;

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
}
