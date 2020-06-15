package com.zh.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button saveBtn;
    Button restoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveBtn = (Button)findViewById(R.id.btn_save);
        restoreBtn = (Button)findViewById(R.id.btn_restore);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "username");
                editor.putInt("age", 21);
                editor.apply();
            }
        });
        restoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", -1);
                Log.d(TAG, "onClick: the name is: " + name);
                Log.d(TAG, "onClick: the age is: " + age);
            }
        });
    }
}
