package ru.gvozdilin.meet.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gvozdilin.meet.entity.Meetup;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetUpMapper implements RowMapper<Meetup> {
    @Override
    public Meetup mapRow(ResultSet rs, int rowNum) throws SQLException {
        Meetup meetup = new Meetup();
        meetup.setMeetUpId(rs.getInt("id"));
        meetup.setTime(rs.getString("time"));
        return meetup;
    }
}
