package com.zh.uidemo;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button titleBackBtn = (Button)findViewById(R.id.title_btn_back);
        Button titleEditBtn = (Button)findViewById(R.id.title_btn_edit);
        titleBackBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        titleEditBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
