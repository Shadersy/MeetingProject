package ru.gvozdilin.meet.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.gvozdilin.meet.entity.User;
import ru.gvozdilin.meet.mapper.UserMapper;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user", new UserMapper());
    }

    @Override
    public User getByLogin(String username) {
        String sql = "SELECT * FROM USER WHERE USERNAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
    }

    public List<User> getUsernameByUserId(Long id) {
        return jdbcTemplate.query("SELECT * from user where id = ?", new Object[]{id}, new UserMapper());
    }
}
