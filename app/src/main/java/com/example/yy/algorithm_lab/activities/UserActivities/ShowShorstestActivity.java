package com.example.yy.algorithm_lab.activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.AdminActivities.AdminMainActivity;
import com.example.yy.algorithm_lab.sys.Dijkstra;
import com.example.yy.algorithm_lab.sys.SiteGraph;

import java.util.zip.Inflater;

public class ShowShorstestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shorstest);

        Intent intent = getIntent();
        Dijkstra dijkstra = (Dijkstra)intent.getSerializableExtra("Dijkstra");
        TextView textView = (TextView)findViewById(R.id.shortest_weight);
        textView.setText(dijkstra.distTo() + "");
        TextView textView1 = (TextView)findViewById(R.id.shortest_line);
        textView1.setText(dijkstra.getLine());



        Button finish = (Button)findViewById(R.id.shortest_finish_btn);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), UserMainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
