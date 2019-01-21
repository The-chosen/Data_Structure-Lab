package com.example.yy.algorithm_lab.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yy.algorithm_lab.R;
import com.example.yy.algorithm_lab.db.Site;

import java.util.List;

public class SiteAdapter extends ArrayAdapter<site_item> {
    private int resourceId;

    public SiteAdapter(Context context, int textViewResourceId, List<site_item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        site_item site_item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView number = (TextView)view.findViewById(R.id.number_item);
        TextView name = (TextView)view.findViewById(R.id.name_item);
        TextView intro = (TextView)view.findViewById(R.id.intro_item);
        number.setText(site_item.getNumber());
        name.setText(site_item.getName());
        intro.setText(site_item.getIntro());
        return view;
    }
}
