package com.zh.forceoffline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineBroadcastReceiver forceOfflineBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zh.forceoffline.FORCE_OFFLINE");
        forceOfflineBroadcastReceiver = new ForceOfflineBroadcastReceiver();
        registerReceiver(forceOfflineBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(forceOfflineBroadcastReceiver != null){
            unregisterReceiver(forceOfflineBroadcastReceiver);
            forceOfflineBroadcastReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    class ForceOfflineBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            Toast.makeText(context, "force offline", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityController.finishAll();
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                }
            });
            builder.show();
        }
    }
}
