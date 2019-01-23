package com.example.yy.algorithm_lab.Android.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author YangYue
 * @description 为获取网端资源做准备
 * @date 2019-2-22 10:00
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
