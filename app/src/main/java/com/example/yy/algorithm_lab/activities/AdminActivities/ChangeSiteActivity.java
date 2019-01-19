package com.example.yy.algorithm_lab.activities.AdminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;

public class ChangeSiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_site);

        Button changeFinish = (Button)findViewById(R.id.site_change_finish_btn);
        changeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                读取数据,信息无误就传入成功界面


//                读取数据,信息有误就传入失败界面


            }
        });
    }
}
