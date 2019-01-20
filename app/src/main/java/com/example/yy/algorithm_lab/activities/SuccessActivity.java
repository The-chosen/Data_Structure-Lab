package com.example.yy.algorithm_lab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.AdminActivities.AdminMainActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Button success = (Button)findViewById(R.id.success_btn);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });
    }
}