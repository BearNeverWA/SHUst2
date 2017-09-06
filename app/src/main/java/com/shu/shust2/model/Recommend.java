package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/4.
 */

public class Recommend {

    private String name;
    private String imageId;
    private String location;
    private int id;

    public Recommend(String imageId, String name, String location, int id) {
        this.imageId = imageId;
        this.name = name;
        this.location = location;
        this.id = id;
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

    public int getId() {
        return id;
    }
}
