package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/4.
 */

public class Recommend {

    private String name;
    private int imageId;

    public Recommend(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
