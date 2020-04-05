package ru.gvozdilin.meet.entity;


import java.sql.Time;

public class User {

private Long id;


private String username;
private String password;

public Long getId() {
    return id;
}

public String getUsername(){
    return username;
}

public String getPassword(){
    return password;
}

private Time time;

public void setTime(Time time){
    this.time=time;
}

public Time getTime(){
    return time;
}

public void setId(Long id){
    this.id=id;
}

public void setUsername(String username){

    this.username=username;
}

public void setPassword(String password){

    this.password=password;
}

}
