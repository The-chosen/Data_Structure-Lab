package com.example.yy.algorithm_lab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.AdminActivities.AdminMainActivity;
import com.example.yy.algorithm_lab.activities.UserActivities.UserMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button user = (Button) findViewById(R.id.activity_main_user_btn);
        Button admin = (Button) findViewById(R.id.activity_main_admin_btn);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserMainActivity.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
