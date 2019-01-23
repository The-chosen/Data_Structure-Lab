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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.Android.db.Site;
import com.example.yy.algorithm_lab.Android.util.DbControl;
import com.example.yy.algorithm_lab.Android.util.HttpUtil;
import com.example.yy.algorithm_lab.Android.util.SiteAdapter;
import com.example.yy.algorithm_lab.Android.util.site_item;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author YangYue
 * @description 搜索景点以及显示景点排序
 * @date 2019-2-22 10:00
 */

public class SiteSearch extends AppCompatActivity {
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_search);

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




        Button search_icon_btn = (Button)findViewById(R.id.seach_icon_btn);
        Button finish_1 = (Button)findViewById(R.id.back_main_1);
        Button finish_2 = (Button)findViewById(R.id.back_main_2);

        List<site_item> ls = DbControl.sortSite();
        SiteAdapter siteAdapter = new SiteAdapter(SiteSearch.this,
                R.layout.site_item, ls);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(siteAdapter);

//        Site[] sites = DbControl.sortSite();
//        String[] siteNames = new String[sites.length];
//        for (int i = 0; i < siteNames.length; i++) {
//            siteNames[i] = sites[i].getName();
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                SiteSearch.this, android.R.layout.simple_list_item_1, siteNames);


        search_icon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout hotSites = (RelativeLayout)findViewById(R.id.hot_sites);
                RelativeLayout haveOutcome = (RelativeLayout)findViewById(R.id.searched_have_outcome);
                RelativeLayout notHaveOutcome = (RelativeLayout)findViewById(R.id.searched_not_have_outcome);
                TextView warning_s = (TextView)findViewById(R.id.warning_search);
                if (((EditText)findViewById(R.id.site_search_entryPlace)).getText().toString().equals("")) {
                    warning_s.setVisibility(View.VISIBLE);
                }
                else {
                    String searchedThing = ((EditText)findViewById(R.id.site_search_entryPlace)).getText().toString();
                    Site site = DbControl.searchSite(searchedThing);
                    if (site == null) {
                        hotSites.setVisibility(View.INVISIBLE);
                        haveOutcome.setVisibility(View.INVISIBLE);
                        notHaveOutcome.setVisibility(View.VISIBLE);

                    }
                    else {
                        TextView showSiteName = (TextView)findViewById(R.id.show_site_name);
                        TextView showSiteIntro = (TextView)findViewById(R.id.show_site_intro);
                        showSiteName.setText(site.getName());
                        showSiteIntro.setText(site.getIntro());
                        hotSites.setVisibility(View.INVISIBLE);
                        haveOutcome.setVisibility(View.VISIBLE);
                        notHaveOutcome.setVisibility(View.INVISIBLE);

                    }
                }
            }
        });

        finish_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserMainActivity.class);
                startActivity(intent);
            }
        });

        finish_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserMainActivity.class);
                startActivity(intent);
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
                        .getDefaultSharedPreferences(SiteSearch.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(SiteSearch.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }


}
