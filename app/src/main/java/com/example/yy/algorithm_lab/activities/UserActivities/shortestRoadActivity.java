package com.example.yy.algorithm_lab.activities.UserActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.sys.Dijkstra;
import com.example.yy.algorithm_lab.util.DbControl;

public class shortestRoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortest_road);

        Button shortest_search = (Button)findViewById(R.id.activity_shortest_search_btn);

        shortest_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning = (TextView) findViewById(R.id.activity_shortest_road_warning);
                if (((EditText)findViewById(R.id.shortest_road_from_entry_content)).getText().toString().equals("")
                        || ((EditText)findViewById(R.id.shortest_road_to_entry_content)).getText().toString().equals("")) {
                    warning.setVisibility(View.VISIBLE);
                }
                else {
                    Dijkstra dijkstra = DbControl.shortest(((EditText)findViewById(R.id.shortest_road_from_entry_content)).getText().toString(),
                            ((EditText)findViewById(R.id.shortest_road_to_entry_content)).getText().toString());
                    Intent intent = new Intent(v.getContext(), ShowShorstestActivity.class);
                    intent.putExtra("Dijkstra", dijkstra);
                    startActivity(intent);
                    warning.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
