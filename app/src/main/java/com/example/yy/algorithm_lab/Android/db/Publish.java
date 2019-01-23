package com.example.yy.algorithm_lab.Android.db;

import org.litepal.crud.LitePalSupport;

/**
 * @author YangYue
 * @description 发布的通告的类
 * @date 2019-2-22 10:00
 */

public class Publish extends LitePalSupport {
    private String publish;

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
