package ru.gvozdilin.meet.entity;

public class Meetup {

    private int meetUpId;
    private String time;


    public void setMeetUpId(int meetUpId){
        this.meetUpId=meetUpId;
    }

    public int getMeetUpId(){
        return meetUpId;
    }

    public void setTime(String time){
        this.time=time;
    }

    public String getTime(){
        return time;
    }
}
