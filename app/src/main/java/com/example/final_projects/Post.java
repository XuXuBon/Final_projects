package com.example.final_projects;


public class Post {
    public String day;
    public String description;
    public String low;
    public String high;
    public String precip;
    public String humidity;

    //對各元件指派資料型態
    public Post(String day, String description, String low, String high, String precip, String humidity) {
        this.day = day;
        this.description = description;
        this.low = low;
        this.high = high;
        this.precip = precip;
        this.humidity = humidity;
    }
}