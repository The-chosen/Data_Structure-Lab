package com.example.yy.algorithm_lab.activities.AdminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;

public class SiteInitializeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_initialize_add_site);

        Button continueAddSite = (Button)findViewById(R.id.add_site_btn_i);
        Button finishAddSite = (Button)findViewById(R.id.finish_site_btn_i);
        Button continueAddEdge = (Button)findViewById(R.id.add_edge_btn_i);
        Button finishAddEdge = (Button)findViewById(R.id.finish_edge_btn_i);

        continueAddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入

                refresh();
            }
        });

        finishAddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入
                setContentView(R.layout.activity_site_initialize_add_edge);
            }
        });

        continueAddEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入

                refresh();
            }
        });

        finishAddEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入

//                成功则进入成功界面

//                失败则进入失败界面

            }
        });
    }

    public void refresh() {
        finish();
        Intent intent = new Intent(SiteInitializeActivity.this, SiteInitializeActivity.class);
        startActivity(intent);
    }
}
