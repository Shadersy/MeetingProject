package ru.gvozdilin.meet.dao;

import ru.gvozdilin.meet.entity.Meetup;
import ru.gvozdilin.meet.entity.MeetupUser;
import java.util.List;

public interface MeetUpDao {
    public List<Meetup> findAllMeetUps();
    public void deleteMeetUp(int id); //удаление встречи
    public long createMeetUp(String time); //создание встречи
    public void setMeetUpUser(Long meetupId, List<Integer> userId); // заполнение промежуточной таблицы
    public List<MeetupUser> findMeetupUsers(); // получение всех пользователей созданных  бд
    public List<MeetupUser> showResultTable(); // join таблиц, для формирования итогового списка встреч и пользователей
}
