package com.Garden.entity;

import com.Garden.repository.Plants;

public class Tree implements Plants {

    protected int height;
    protected String name;

    public void printPlantName() {
        System.out.print("Plant name: " + name);
    }

    public Tree(int height, String name) {
        this.height = height;
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
