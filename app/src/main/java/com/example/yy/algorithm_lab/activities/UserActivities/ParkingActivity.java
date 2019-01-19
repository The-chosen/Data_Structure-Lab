package com.example.yy.algorithm_lab.activities.UserActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yy.algorithm_lab.R;

public class ParkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        Button enterBtn = (Button)findViewById(R.id.activity_parking_enter_btn);
        Button outBtn = (Button)findViewById(R.id.activity_psrking_out_btn);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_parking_enter);
            }
        });

        outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_parking_out);
            }
        });
    }
}
