package com.example.TrelloClone.Services;

import com.example.TrelloClone.Helpers.Task;
import com.example.TrelloClone.Helpers.State;

import java.sql.Time;
import java.time.LocalTime;

public interface MainService {
    String findByName(Long suid);
    Iterable<Task> findAll();
    void save(Task s);
    Long findtaskID(Long id);
    void deleteCommentById(Long taskID);
    void deleteById(Long taskID);
    LocalTime fetchTime(Long id);
    Long fetchTimeInTodo(Long id);
    Long fetchTimeInDoing(Long id);
    Long fetchTimeInDone(Long id);
    void updateTimestamp(Long id, Time time);
    void addCompletionTime(Long id, Long time);
    void timeInTodo(Long id, Long time);
    void timeInDoing(Long id, Long time);
    void timeInDone(Long id, Long time);
    void changestate(Long id, String string);
    void changeusername(Long id, Long newsuid);
    void changename(Long id, String name);
    State getCurrentState(Long taskid);
    void changedesc(Long id, String desc);
    void addComment(Long id, String comment);
}
