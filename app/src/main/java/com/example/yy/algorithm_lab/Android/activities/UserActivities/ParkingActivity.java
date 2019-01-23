package com.example.yy.algorithm_lab.Android.activities.UserActivities;

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
import com.example.yy.algorithm_lab.Android.util.DbControl;
import com.example.yy.algorithm_lab.Android.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author YangYue
 * @description 停车界面，需要输入车牌号
 * @date 2019-2-22 10:00
 */

public class ParkingActivity extends AppCompatActivity {
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        Button enterBtn = (Button)findViewById(R.id.activity_parking_enter_btn);
        Button outBtn = (Button)findViewById(R.id.activity_psrking_out_btn);
        final TextView warning_p = (TextView)findViewById(R.id.warning_parking);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EditText)findViewById(R.id.car_number_content)).getText().toString().equals("")) {
                    warning_p.setVisibility(View.VISIBLE);
                }
                else {
                    String number = ((EditText)findViewById(R.id.car_number_content)).getText().toString();
                    String rest = DbControl.enterParkingLot(Integer.parseInt(number)) + "";
                    Intent intent = new Intent(v.getContext(), EnterParkingActivity.class);
                    intent.putExtra("rest", rest);
                    startActivity(intent);
                    warning_p.setVisibility(View.INVISIBLE);
                }
            }
        });

        outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EditText)findViewById(R.id.car_number_content)).getText().toString().equals("")) {
                    warning_p.setVisibility(View.VISIBLE);
                }
                else {
                    String number = ((EditText)findViewById(R.id.car_number_content)).getText().toString();
                    String price = DbControl.outParkingLot(Integer.parseInt(number)) + "";
                    Intent intent = new Intent(v.getContext(), OutParkingActivity.class);
                    intent.putExtra("price", price);
                    startActivity(intent);
                    warning_p.setVisibility(View.INVISIBLE);
                }
            }
        });


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
                        .getDefaultSharedPreferences(ParkingActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ParkingActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}
