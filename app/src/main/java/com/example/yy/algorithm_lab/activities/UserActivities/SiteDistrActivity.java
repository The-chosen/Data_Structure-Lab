package com.example.yy.algorithm_lab.activities.UserActivities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.db.Site;
import com.example.yy.algorithm_lab.sys.SiteGraph;
import com.example.yy.algorithm_lab.util.DbControl;
import com.example.yy.algorithm_lab.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SiteDistrActivity extends AppCompatActivity {
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_distr);

        String[] names = DbControl.getNames();
        SiteGraph siteGraph = DbControl.SiteDistr();
        TableLayout table = (TableLayout)findViewById(R.id.table);

        TableRow tr = new TableRow(this);
        TextView white = new TextView(this);
        tr.addView(white);

        for (int i = 0; i < names.length; i++) {
            TextView name = new TextView(this);
            name.setPadding(10, 5, 10, 5);
            name.setGravity(Gravity.CENTER_HORIZONTAL);
            name.setTextColor(Color.WHITE);
            name.setText(names[i]);
            tr.addView(name);
        }
        table.addView(tr);

        for (int i = 0; i < names.length; i++) {
            TableRow newLine = new TableRow(this);
            TextView name = new TextView(this);
            name.setPadding(10, 5, 10, 5);
            name.setGravity(Gravity.CENTER_HORIZONTAL);
            name.setTextColor(Color.WHITE);
            name.setText(names[i]);
            newLine.addView(name);
            for (int j = 0; j < names.length; j++) {
                TextView weight = new TextView(this);
                weight.setPadding(10, 5, 10, 5);
                weight.setGravity(Gravity.CENTER_HORIZONTAL);
                weight.setTextColor(Color.WHITE);
                String content = "";
                if (Math.abs(siteGraph.getMatrix()[i][j].getWeight() - 32767) < 0.01) {
                    content = "不通";
                }
                else {
                    content = siteGraph.getMatrix()[i][j].getWeight() + "";
                }
                weight.setText(content);
                newLine.addView(weight);
            }
            table.addView(newLine);
        }

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
                        .getDefaultSharedPreferences(SiteDistrActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(SiteDistrActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }

}
