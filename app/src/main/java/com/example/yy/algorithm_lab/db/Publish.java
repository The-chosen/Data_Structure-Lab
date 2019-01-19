package com.example.yy.algorithm_lab.db;

import org.litepal.crud.DataSupport;

public class Publish extends DataSupport {
    private String publish;

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
