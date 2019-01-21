package com.example.yy.algorithm_lab.activities.AdminActivities;

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
import com.example.yy.algorithm_lab.activities.SuccessActivity;
import com.example.yy.algorithm_lab.activities.UserActivities.SiteDistrActivity;
import com.example.yy.algorithm_lab.db.Site;
import com.example.yy.algorithm_lab.util.DbControl;
import com.example.yy.algorithm_lab.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddEdgeActivity extends AppCompatActivity {
    private ImageView bingPicImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edge);
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




        final String fromName = (String)getIntent().getSerializableExtra("newSite");

        Button newEdgeAdd = (Button)findViewById(R.id.new_edge_add_btn);
        Button newEdgeAddFinish = (Button)findViewById(R.id.new_edge_add_finish_btn);

        newEdgeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning_a = (TextView)findViewById(R.id.warning_add_edge);
                if (((EditText)findViewById(R.id.connect_site_content)).getText().toString().equals("")
                        || ((EditText)findViewById(R.id.new_edge_weight_content)).getText().toString().equals("")) {
                    warning_a.setVisibility(View.VISIBLE);
                }
                else {
                    String toName = ((EditText)findViewById(R.id.connect_site_content)).getText().toString();
                    double weight = Double.parseDouble(((EditText)findViewById(R.id.new_edge_weight_content)).getText().toString());
                    DbControl.addNewEdge(fromName, toName, weight);
                    warning_a.setVisibility(View.INVISIBLE);
                    refresh();
                }
            }
        });

        newEdgeAddFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                如果信息无误，跳转成功界面
                TextView warning_a = (TextView)findViewById(R.id.warning_add_edge);
                if (((EditText)findViewById(R.id.connect_site_content)).getText().toString().equals("")
                        || ((EditText)findViewById(R.id.new_edge_weight_content)).getText().toString().equals("")) {
                    warning_a.setVisibility(View.VISIBLE);
                }
                else {
                    String toName = ((EditText)findViewById(R.id.connect_site_content)).getText().toString();
                    double weight = Double.parseDouble(((EditText)findViewById(R.id.new_edge_weight_content)).getText().toString());
                    DbControl.addNewEdge(fromName, toName, weight);
                    warning_a.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(v.getContext(), SuccessActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void refresh() {
        finish();
        Intent intent = new Intent(AddEdgeActivity.this, AddEdgeActivity.class);
        startActivity(intent);
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
                        .getDefaultSharedPreferences(AddEdgeActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(AddEdgeActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}
