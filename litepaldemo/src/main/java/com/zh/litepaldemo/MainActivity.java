package com.zh.litepaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_create)
    Button btnCreate;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_query)
    Button btnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vince Code");
                book.setAuthor("Da Brown");
                book.setPages(368);
                book.setPrice(36.58);
                book.setPress("Unknow");
                book.save();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("Chun");
                book.setPages(633);
                book.setPrice(45);
                book.setPress("Unknow");
                book.updateAll("name = ? and author = ? ", "Python核心编程", "Chun");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.delete(Book.class, 2);
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for(Book item:books){
                    Log.d(TAG, "the name is: " + item.getName());
                }
            }
        });

    }
}
