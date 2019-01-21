package com.example.yy.algorithm_lab.activities.UserActivities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.LoginActivity;
import com.example.yy.algorithm_lab.util.DbControl;
import com.example.yy.algorithm_lab.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShowPublishingActivity extends AppCompatActivity {
    private String[] publishLs = DbControl.getPublish();
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_publishing);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ShowPublishingActivity.this, R.layout.my_simple_list_item_1, publishLs
        );
        ListView listView = (ListView)findViewById(R.id.show_publishing);
        listView.setAdapter(adapter);


        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String bingPic = prefs.getString("bing_pic", null);
        bingPicImg = (ImageView)findViewById(R.id.bing_pic_img);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        }
        else {
            loadBingPic();
        }
    }

    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(ShowPublishingActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ShowPublishingActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}
