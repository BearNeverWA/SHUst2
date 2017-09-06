package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/4.
 */

public class Hot {

    private String clubLogo;
    private String clubName;
    private int clubStar;
    private int id;

    public Hot(String clubLogo, String clubName, int clubStar, int id) {
        this.clubLogo = clubLogo;
        this.clubName = clubName;
        this.clubStar = clubStar;
        this.id = id;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public String getClubName() {
        return clubName;
    }

    public int getClubStar() {
        return clubStar;
    }

    public int getId() {
        return id;
    }
}
