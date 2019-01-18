package com.example.yy.algorithm_lab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.sys.Site;

public class AddEdgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edge);

        Site site = (Site)getIntent().getSerializableExtra("newSite");

        Button newEdgeAdd = (Button)findViewById(R.id.new_edge_add_btn);
        Button newEdgeAddFinish = (Button)findViewById(R.id.new_edge_add_finish_btn);

        newEdgeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_add_edge);
//                收集数据

            }
        });

        newEdgeAddFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                如果信息无误，跳转成功界面


//                如果信息有误，跳转失败界面
            }
        });
    }
}
