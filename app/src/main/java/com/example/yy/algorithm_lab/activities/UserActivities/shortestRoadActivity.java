package com.example.yy.algorithm_lab.activities.UserActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.sys.Dijkstra;
import com.example.yy.algorithm_lab.util.DbControl;
import com.example.yy.algorithm_lab.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class shortestRoadActivity extends AppCompatActivity {
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortest_road);

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

        Button shortest_search = (Button)findViewById(R.id.activity_shortest_search_btn);

        shortest_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning = (TextView) findViewById(R.id.activity_shortest_road_warning);
                if (((EditText)findViewById(R.id.shortest_road_from_entry_content)).getText().toString().equals("")
                        || ((EditText)findViewById(R.id.shortest_road_to_entry_content)).getText().toString().equals("")) {
                    warning.setVisibility(View.VISIBLE);
                }
                else {
                    Dijkstra dijkstra = DbControl.shortest(((EditText)findViewById(R.id.shortest_road_from_entry_content)).getText().toString(),
                            ((EditText)findViewById(R.id.shortest_road_to_entry_content)).getText().toString());
                    Intent intent = new Intent(v.getContext(), ShowShorstestActivity.class);
                    intent.putExtra("Dijkstra", dijkstra);
                    startActivity(intent);
                    warning.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                        .getDefaultSharedPreferences(shortestRoadActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(shortestRoadActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}
