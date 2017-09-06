package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/4.
 */

public class Recommend {

    private String name;
    private String imageId;
    private String location;

    public Recommend(String imageId, String name, String location) {
        this.imageId = imageId;
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getImageId() {
        return imageId;
    }

    public String getLocation() {
        return location;
    }
}
