package com.example.yy.algorithm_lab.activities.AdminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.activities.SuccessActivity;
import com.example.yy.algorithm_lab.util.DbControl;

public class EdgeInitializeActivity extends AppCompatActivity {
    private String from;
    private String to;
    private double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_initialize);

        Button continueAddEdge = (Button)findViewById(R.id.add_edge_btn_i);
        Button finishAddEdge = (Button)findViewById(R.id.finish_edge_btn_i);


        continueAddEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入
                TextView warning_e_i = (TextView)findViewById(R.id.warning_e_i);
                if (((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.weight_edit_i)).getText().toString().equals("")) {
                    warning_e_i.setVisibility(View.VISIBLE);
                }
                else {
                    from = ((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString();
                    to = ((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString();
                    weight = Double.parseDouble(((EditText)findViewById(R.id.weight_edit_i)).getText().toString());
                    DbControl.addNewEdge(from, to, weight);
                    refresh();
                }
            }
        });

        finishAddEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                向数据库写入输入
                TextView warning_e_i = (TextView)findViewById(R.id.warning_e_i);
                if (((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.weight_edit_i)).getText().toString().equals("")) {
                    warning_e_i.setVisibility(View.VISIBLE);
                }
                else {
                    from = ((EditText)findViewById(R.id.from_entry_edit_i)).getText().toString();
                    to = ((EditText)findViewById(R.id.to_entry_edit_i)).getText().toString();
                    weight = Double.parseDouble(((EditText)findViewById(R.id.weight_edit_i)).getText().toString());
                    DbControl.addNewEdge(from, to, weight);
                    Intent intent = new Intent(v.getContext(), SuccessActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void refresh() {
        finish();
        Intent intent = new Intent(EdgeInitializeActivity.this, EdgeInitializeActivity.class);
        startActivity(intent);
    }
}
