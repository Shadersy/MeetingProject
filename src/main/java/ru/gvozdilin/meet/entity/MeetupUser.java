package ru.gvozdilin.meet.entity;

public class MeetupUser {
    private int meetupId;
    private int userId;
    private String username;
    private String time;

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int user_id){
        this.userId =user_id;
    }

    public int getMeetupId(){
        return meetupId;
    }

    public void setMeetupId(int meetup_id){
        this.meetupId =meetup_id;
    }
}
