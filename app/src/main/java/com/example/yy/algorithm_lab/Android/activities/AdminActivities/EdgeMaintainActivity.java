package com.example.yy.algorithm_lab.Android.activities.AdminActivities;

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
import com.example.yy.algorithm_lab.Android.activities.InitialActivities.SuccessActivity;
import com.example.yy.algorithm_lab.Android.util.DbControl;
import com.example.yy.algorithm_lab.Android.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author YangYue
 * @description 维护道路的界面
 * @date 2019-2-22 10:00
 */

public class EdgeMaintainActivity extends AppCompatActivity {
    private String from;
    private String to;
    private double weight;
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_maintain);


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



        Button addEdge = (Button)findViewById(R.id.add_edge_btn);
        Button deleteEdge = (Button)findViewById(R.id.delete_edge_btn);

        addEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning_e_i = (TextView)findViewById(R.id.warning_edge_maintain);
                if (((EditText)findViewById(R.id.from_entry_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.to_entry_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.weight_content)).getText().toString().equals("")) {
                    warning_e_i.setVisibility(View.VISIBLE);
                }
                else {
                    from = ((EditText)findViewById(R.id.from_entry_content)).getText().toString();
                    to = ((EditText)findViewById(R.id.to_entry_content)).getText().toString();
                    weight = Double.parseDouble(((EditText)findViewById(R.id.weight_content)).getText().toString());
                    DbControl.addNewEdge(from, to, weight);
                    Intent intent = new Intent(v.getContext(), SuccessActivity.class);
                    startActivity(intent);
                }
            }
        });

        deleteEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning_e_i = (TextView)findViewById(R.id.warning_edge_maintain);
                if (((EditText)findViewById(R.id.from_entry_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.to_entry_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.weight_content)).getText().toString().equals("")) {
                    warning_e_i.setVisibility(View.VISIBLE);
                }
                else {
                    from = ((EditText)findViewById(R.id.from_entry_content)).getText().toString();
                    to = ((EditText)findViewById(R.id.to_entry_content)).getText().toString();
                    weight = Double.parseDouble(((EditText)findViewById(R.id.weight_content)).getText().toString());
                    DbControl.deleteEdge(from, to);
                    Intent intent = new Intent(v.getContext(), SuccessActivity.class);
                    startActivity(intent);
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
                        .getDefaultSharedPreferences(EdgeMaintainActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(EdgeMaintainActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }

}
