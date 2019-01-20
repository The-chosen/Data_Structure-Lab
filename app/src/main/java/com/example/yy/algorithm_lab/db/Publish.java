package com.example.yy.algorithm_lab.db;

import org.litepal.crud.LitePalSupport;

public class Publish extends LitePalSupport {
    private String publish;

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
