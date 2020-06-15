package com.zh.cardviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Fruit[] fruits = {new Fruit("Orange", R.drawable.orange), new Fruit("Apple", R.drawable.apple),
    new Fruit("Bananas", R.drawable.bananas), new Fruit("Blackberry", R.drawable.blackberries),
    new Fruit("Cherry", R.drawable.cherry), new Fruit("Lime", R.drawable.lime), new Fruit("Pineapple", R.drawable.pineapple),
    new Fruit("Plums", R.drawable.plums), new Fruit("Pomegranate", R.drawable.pomegranate),
    new Fruit("Raspberry", R.drawable.raspberry), new Fruit("Strawberry", R.drawable.strawberry),
    new Fruit("Watermelon", R.drawable.watermelon)};

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.aquamarine);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

        initFruits();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new FruitAdapter(fruitList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void initFruits(){
        for(int i = 0; i < 60; i++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fruitList.clear();
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
