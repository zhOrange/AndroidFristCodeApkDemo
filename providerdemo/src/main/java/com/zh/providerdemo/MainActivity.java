package com.zh.providerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_delete)
    Button btnDelete;

    private String newId;
    private static final String TargetContentProviderAuthority = "content://com.zh.databasedemo.provider";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(TargetContentProviderAuthority + "/book");
                ContentValues values = new ContentValues();
                values.put("name", "算法导论");
                values.put("author", "Stein");
                values.put("pages", 982);
                values.put("price", 68.36);
                Uri neeUri = getContentResolver().insert(uri, values);
                newId = neeUri.getPathSegments().get(1);
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(TargetContentProviderAuthority + "/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if(cursor != null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d(TAG, "the name of book is: " + name);
                    }
                    cursor.close();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(TargetContentProviderAuthority + "/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("price", 128.56);
                values.put("pages", 1128);
                getContentResolver().update(uri, values, null, null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(TargetContentProviderAuthority + "/book/" + newId);
                getContentResolver().delete(uri, "pages < ?", new String[]{"300"});
            }
        });
    }
}
