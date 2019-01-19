package com.example.yy.algorithm_lab.activities.AdminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        Button initialize = (Button)findViewById(R.id.activity_admin_main_initialize_btn);
        Button insertSite = (Button)findViewById(R.id.activity_admin_main_site_btn);
        Button insertEdge = (Button)findViewById(R.id.activity_admin_main_edge_btn);
        Button publish = (Button)findViewById(R.id.activity_admin_main_publish_btn);

        initialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SiteInitializeActivity.class);
                startActivity(intent);
            }
        });

        insertSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SiteMaintainActivity.class);
                startActivity(intent);
            }
        });

        insertEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EdgeMaintainActivity.class);
                startActivity(intent);
            }
        });

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PublishActivity.class);
                startActivity(intent);
            }
        });
    }
}
