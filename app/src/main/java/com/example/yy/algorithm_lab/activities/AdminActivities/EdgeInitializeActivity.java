package com.example.yy.algorithm_lab.activities.AdminActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.SuccessActivity;
import com.example.yy.algorithm_lab.activities.UserActivities.SiteDistrActivity;
import com.example.yy.algorithm_lab.util.DbControl;
import com.example.yy.algorithm_lab.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EdgeInitializeActivity extends AppCompatActivity {
    private String from;
    private String to;
    private ImageView bingPicImg;
    private double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_initialize);


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


        Button continueAddEdge = (Button)findViewById(R.id.add_edge_btn_i);
        Button finishAddEdge = (Button)findViewById(R.id.finish_edge_btn_i);


        continueAddEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入
                TextView warning_e_i = (TextView)findViewById(R.id.warning_e_i);
                if (((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.weight_edit_i)).getText().toString().equals("")) {
                    warning_e_i.setVisibility(View.VISIBLE);
                }
                else {
                    from = ((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString();
                    to = ((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString();
                    weight = Double.parseDouble(((EditText)findViewById(R.id.weight_edit_i)).getText().toString());
                    DbControl.addNewEdge(from, to, weight);
                    refresh();
                }


            }
        });

        finishAddEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入
                TextView warning_e_i = (TextView)findViewById(R.id.warning_e_i);
                if (((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.weight_edit_i)).getText().toString().equals("")) {
                    warning_e_i.setVisibility(View.VISIBLE);
                }
                else {
                    from = ((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString();
                    to = ((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString();
                    weight = Double.parseDouble(((EditText)findViewById(R.id.weight_edit_i)).getText().toString());
                    DbControl.addNewEdge(from, to, weight);
                    Intent intent = new Intent(v.getContext(), SuccessActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void refresh() {
        finish();
        Intent intent = new Intent(EdgeInitializeActivity.this, EdgeInitializeActivity.class);
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
                        .getDefaultSharedPreferences(EdgeInitializeActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(EdgeInitializeActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

}
