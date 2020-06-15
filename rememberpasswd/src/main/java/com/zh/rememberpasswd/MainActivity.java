package com.zh.rememberpasswd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.user_name)
    EditText userName;
    EditText passwd;
    CheckBox rememberPass;
    Button btnLogin;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pref = PreferenceManager.getDefaultSharedPreferences(this);


        userName = (EditText)findViewById(R.id.user_name);
        passwd = (EditText)findViewById(R.id.user_passwd);
        rememberPass = (CheckBox)findViewById(R.id.isRemember);
        btnLogin = (Button)findViewById(R.id.btn_login);

        boolean isRemember = pref.getBoolean("remember_passwd", false);
        if(isRemember){
            String nameStr = pref.getString("user", "");
            String passwdStr = pref.getString("passwd", "");
            userName.setText(nameStr);
            passwd.setText(passwdStr);
            rememberPass.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String passwdStr = passwd.getText().toString();
                if("admin".equals(name) && "123456".equals(passwdStr)){

                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_passwd", true);
                        editor.putString("user", name);
                        editor.putString("passwd", passwdStr);
                        editor.apply();
                    }else {
                        editor.clear();
                        editor.apply();
                    }
                    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
