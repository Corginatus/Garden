package com.Garden.entity;

import com.Garden.entity.Tree;

import java.util.List;

public class Root {
    private String name;
    private List<Tree> tree;
    private List<Bush> bush;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tree> getTree() {
        return tree;
    }

    public void setTree(List<Tree> plants) {
        this.tree = plants;
    }

    public List<Bush> getBush() {
        return bush;
    }

    public void setBush(List<Bush> bush) {
        this.bush = bush;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", plants=" + tree +
                '}';
    }
}
