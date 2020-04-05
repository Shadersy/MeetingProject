package ru.gvozdilin.meet.dao;

import ru.gvozdilin.meet.entity.User;
import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public User getByLogin(String username); //метод для авторизации пользователя, созданного в бд
    public List<User> getUsernameByUserId(Long id); //получить username авторизированного пользователя
}
