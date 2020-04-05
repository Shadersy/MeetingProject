package ru.gvozdilin.meet.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gvozdilin.meet.entity.MeetupUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetupUserMapper implements RowMapper<MeetupUser> {
    @Override
    public MeetupUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeetupUser meetup_user = new MeetupUser();
        meetup_user.setMeetupId(rs.getInt("meetup_id"));
        meetup_user.setUserId(rs.getInt("user_id"));
        return  meetup_user;
    }
}
