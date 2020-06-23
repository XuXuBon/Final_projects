package com.example.final_projects;

public class Air_Post {

    public String departure_air;
    public String arrival_air;
    public String start_date;
    public String end_date;
    public String start_time;
    public String end_time;
    public String air_id;
    public String update_time;

    //對各元件指派資料型態
    public Air_Post(String departure_air, String arrival_air , String start_date, String end_date, String start_time, String end_time, String air_id, String update_time) {
        this.departure_air = departure_air;
        this.arrival_air = arrival_air;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.air_id = air_id;
        this.update_time = update_time;
    }
}
