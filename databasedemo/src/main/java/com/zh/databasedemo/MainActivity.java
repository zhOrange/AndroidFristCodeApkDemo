package com.zh.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_create)
    Button btn_create;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.btn_delete)
    Button btn_delete;
    @BindView(R.id.btn_query)
    Button btn_query;

    private MySQListeOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dbHelper = new MySQListeOpenHelper(MainActivity.this, "Bookstore.db", null, 2);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vince Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 12.5);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 256);
                values.put("price", 16.9);
                db.insert("Book", null, values);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put("price", 38);
                db.update("Book", value, "name=?", new String[]{"The Da Vince Code"});
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages>?", new String[]{"300"});
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d(TAG, "the name is: " + name);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }

}
