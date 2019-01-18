package com.example.yy.algorithm_lab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;

public class DeleteSiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_site);

        Button deleteSite = (Button)findViewById(R.id.delete_site_finish_btn);

        deleteSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                信息无误则跳转到成功界面

//                信息有误则跳转到失败界面

            }
        });
    }
}
