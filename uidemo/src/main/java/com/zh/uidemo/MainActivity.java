package com.zh.uidemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText edit;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!= null){
            actionBar.hide();
        }
        edit = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = edit.getText().toString();
//                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View v){
        Log.d(TAG, "onClick: Clicked.");
        switch (v.getId()){
            case R.id.btn:
                Log.d(TAG, "onClick: ");
                createProgressDialog(MainActivity.this);
                break;
            default:
                break;
        }
    }

    private void createAlertDialog(Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("This is Dialog");
        dialog.setMessage("sth important.");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private void createProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Prograss Dialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

}
