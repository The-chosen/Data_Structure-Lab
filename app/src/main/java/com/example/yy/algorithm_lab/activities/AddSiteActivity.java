package com.example.yy.algorithm_lab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.sys.Site;

public class AddSiteActivity extends AppCompatActivity {
    private int id;
    private String name;
    private String intro;
    private boolean hasBreak;
    private boolean hasWC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        Button siteAddFinish = (Button)findViewById(R.id.site_add_finish_btn);
        final RadioGroup restRadioGroup = (RadioGroup)findViewById(R.id.rest_radiogroup);
        final RadioGroup wcRadioGroup = (RadioGroup)findViewById(R.id.wc_radiogroup);

        siteAddFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView warning_a = (TextView)findViewById(R.id.warning_a);
//                将管理员新增的景点信息传入增加新路径的界面，然后跳转
                if (((EditText)findViewById(R.id.id_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.name_content)).getText().toString().equals("")
                        ||((EditText)findViewById(R.id.intro_content)).getText().toString().equals("")
                        ||((RadioButton)findViewById(restRadioGroup.getCheckedRadioButtonId())).equals(null)
                        ||((RadioButton)findViewById(wcRadioGroup.getCheckedRadioButtonId())).equals(null)){
                    warning_a.setVisibility(View.VISIBLE);
                }
                else {
                    id = Integer.parseInt(((EditText)findViewById(R.id.id_content)).getText().toString());
                    name = ((EditText)findViewById(R.id.name_content)).getText().toString();
                    intro = ((EditText)findViewById(R.id.intro_content)).getText().toString();
                    RadioButton rest_rb = (RadioButton)findViewById(restRadioGroup.getCheckedRadioButtonId());
                    hasBreak = ("rest_y" == rest_rb.getText().toString());
                    RadioButton wc_rb = (RadioButton)findViewById(wcRadioGroup.getCheckedRadioButtonId());
                    hasBreak = ("wc_y" == wc_rb.getText().toString());
                    Site site = new Site(id, name, intro, hasBreak, hasWC);
                    Intent intent = new Intent(v.getContext(), AddEdgeActivity.class);
                    intent.putExtra("newSite", site);
                    startActivity(intent);
                    warning_a.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
