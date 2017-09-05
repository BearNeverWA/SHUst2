package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/4.
 */

public class Club {

    private int clubLogo;
    private String clubName;
    private String clubType;
    private int clubStar;
    private String clubIntro;

    public Club(int clubLogo, String clubName, String clubType, int clubStar, String clubIntro) {
        this.clubLogo = clubLogo;
        this.clubName = clubName;
        this.clubType = clubType;
        this.clubStar = clubStar;
        this.clubIntro = clubIntro;
    }

    public int getClubLogo() {
        return clubLogo;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubType() {
        return clubType;
    }

    public int getClubStar() {
        return clubStar;
    }

    public String getClubIntro() {
        return clubIntro;
    }
}
