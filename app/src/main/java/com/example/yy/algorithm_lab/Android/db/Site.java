package com.example.yy.algorithm_lab.Android.db;

import android.support.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

/**
 * @author YangYue
 * @description 景点类
 * @date 2019-2-22 10:00
 */

public class Site extends LitePalSupport implements Serializable, Comparable<Site>{
    private int id;
    private int myId;
    private String name;
    private String intro;
    private int popul;
    private boolean hasBreak;
    private boolean hasWC;
    private List<DiEdge> diEdges;

    public Site() {}

    public Site(int myId, String name) {
        this.myId = myId;
        this.name = name;
    }

    public Site(int myId, String name, String intro, boolean hasBreak, boolean hasWC) {
        this.myId = myId;
        this.name = name;
        this.intro = intro;
        this.hasBreak = hasBreak;
        this.hasWC = hasWC;
    }

    public Site(String name, String intro, boolean hasBreak, boolean hasWC) {
        this.name = name;
        this.intro = intro;
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

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
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
