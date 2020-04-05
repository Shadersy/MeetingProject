package ru.gvozdilin.meet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gvozdilin.meet.entity.Meetup;
import ru.gvozdilin.meet.entity.MeetupUser;
import ru.gvozdilin.meet.mapper.MeetUpMapper;
import ru.gvozdilin.meet.mapper.MeetupUserMapper;
import ru.gvozdilin.meet.mapper.ResultMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class MeetsUpDaoImpl implements MeetUpDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    MeetsUpDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Meetup> findAllMeetUps() {
        return jdbcTemplate.query("SELECT * FROM meetup", new MeetUpMapper());
    }


    public void deleteMeetUp(int id) {
        jdbcTemplate.update("DELETE FROM meetup_user WHERE meetup_id = ?", id);
        jdbcTemplate.update("DELETE FROM meetup WHERE id = ?", id);
    }

    public long createMeetUp(String time) {
        long key = -1L;
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("INSERT INTO meetup (time) VALUES (?) ", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, time);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                key = rs.getLong(1);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
                return key;
    }

    public void setMeetUpUser(Long meetupId, List<Integer> userId){

        for (Integer s: userId) {
            jdbcTemplate.update("INSERT INTO meetup_user (meetup_id, user_id) VALUES (?, ?)", meetupId, s);
        }
    }

    @Override
    public List<MeetupUser> findMeetupUsers() {
        return jdbcTemplate.query("SELECT * FROM meetup_user;", new MeetupUserMapper());
    }

    public List<MeetupUser> showResultTable(){
        return jdbcTemplate.query("SELECT time, username, meetup_id, user_id from meetup LEFT JOIN meetup_user mu on meetup.id = mu.meetup_id LEFT JOIN user u on mu.user_id = u.id", new ResultMapper());
    }
}



