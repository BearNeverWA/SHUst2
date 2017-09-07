package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/4.
 */

public class Activity {

    private String activityLogo;
    private String activityName;
    private String activityType;
    private String activityStatus;
    private String activityLocation;
    private int id;

    public Activity(String activityLogo, String activityName, String activityType, String activityStatus, String activityLocation, int id) {
        this.activityLogo = activityLogo;
        this.activityName = activityName;
        this.activityType = activityType;
        this.activityStatus = activityStatus;
        this.activityLocation = activityLocation;
        this.id = id;
    }

    public String getActivityLogo() {
        return activityLogo;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public int getId() {
        return id;
    }
}
