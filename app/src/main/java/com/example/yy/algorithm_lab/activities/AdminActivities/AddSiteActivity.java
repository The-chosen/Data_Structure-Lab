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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.UserActivities.SiteDistrActivity;
import com.example.yy.algorithm_lab.db.Site;
import com.example.yy.algorithm_lab.util.DbControl;
import com.example.yy.algorithm_lab.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddSiteActivity extends AppCompatActivity {
    private ImageView bingPicImg;
    private String name;
    private String intro;
    private boolean hasBreak;
    private boolean hasWC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
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

        Button siteAddFinish = (Button)findViewById(R.id.site_add_finish_btn);
        final RadioGroup restRadioGroup = (RadioGroup)findViewById(R.id.rest_radiogroup);
        final RadioGroup wcRadioGroup = (RadioGroup)findViewById(R.id.wc_radiogroup);

        siteAddFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning_a = (TextView)findViewById(R.id.warning_a);
//                将管理员新增的景点信息传入增加新路径的界面，然后跳转
                if (((EditText)findViewById(R.id.name_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.intro_content)).getText().toString().equals("")
                        ||((RadioButton)findViewById(restRadioGroup.getCheckedRadioButtonId())) == null
                        ||((RadioButton)findViewById(wcRadioGroup.getCheckedRadioButtonId())) == null){
                    warning_a.setVisibility(View.VISIBLE);
                }
                else {
//                    这里需要添加id，从数据库里读取
                    name = ((EditText)findViewById(R.id.name_content)).getText().toString();
                    intro = ((EditText)findViewById(R.id.intro_content)).getText().toString();
                    RadioButton rest_rb = (RadioButton)findViewById(restRadioGroup.getCheckedRadioButtonId());
                    hasBreak = ("rest_y".equals(rest_rb.getText().toString()));
                    RadioButton wc_rb = (RadioButton)findViewById(wcRadioGroup.getCheckedRadioButtonId());
                    hasWC = ("wc_y".equals( wc_rb.getText().toString()));
                    System.out.println("QQQ:" + name);
                    System.out.println("QQQ:" + intro);
                    System.out.println("QQQ:" + hasBreak);
                    System.out.println("QQQ:" + hasWC);
                    DbControl.addNewSite(name, intro, hasBreak, hasWC);
                    Intent intent = new Intent(v.getContext(), AddEdgeActivity.class);
                    intent.putExtra("newSite", name);
                    startActivity(intent);
                    warning_a.setVisibility(View.INVISIBLE);
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
                        .getDefaultSharedPreferences(AddSiteActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(AddSiteActivity.this).load(bingPic).into(bingPicImg);
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
