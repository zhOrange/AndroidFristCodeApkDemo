package com.zh.recyclerviewdemo;

public class Fruit {
    String name;
    int imageId;
    public Fruit(String name, int id){
        this.name = name;
        this.imageId = id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
