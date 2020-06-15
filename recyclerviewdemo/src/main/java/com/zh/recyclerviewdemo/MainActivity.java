package com.zh.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FruitAdapter adapter = new FruitAdapter(fruits);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits(){
        for(int  i = 0; i < 5; i++){
            fruits.add(new Fruit("1", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("2", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("3", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("4", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("5", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("6", R.drawable.ic_launcher_foreground));
        }
    }

}
