package com.example.yy.algorithm_lab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;

public class SiteMaintainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_maintain);

        Button add_site = (Button)findViewById(R.id.activity_site_maintian_insert_btn);
        Button change_site = (Button)findViewById(R.id.activity_site_maintain_change_btn);
        Button delete_site = (Button)findViewById(R.id.activity_site_maintain_delete_btn);

        add_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddSiteActivity.class);
                startActivity(intent);
            }
        });

        change_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChangeSiteActivity.class);
                startActivity(intent);
            }
        });

        delete_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DeleteSiteActivity.class);
                startActivity(intent);
            }
        });
    }
}
