package com.zh.forceoffline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    private EditText userText;
    private EditText passwdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userText = (EditText)findViewById(R.id.user_text);
        passwdText = (EditText)findViewById(R.id.passwd_text);
        if(savedInstanceState != null){
            userText.setText(savedInstanceState.getString("userName"));
            passwdText.setText(savedInstanceState.getString("passwd"));
        }
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userText.getText().toString();
                String passwd = passwdText.getText().toString();
                if("admin".equals(name) && "12345678".equals(passwd)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String userName = userText.getText().toString();
        String passwd = passwdText.getText().toString();
        outState.putString("userName", userName);
        outState.putString("passwd", passwd);
        Log.d(TAG, "onSaveInstanceState: ");
    }
}
