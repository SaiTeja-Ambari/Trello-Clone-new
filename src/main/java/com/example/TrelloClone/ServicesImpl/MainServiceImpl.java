package com.example.TrelloClone.ServicesImpl;

import com.example.TrelloClone.Helpers.State;
import com.example.TrelloClone.Helpers.Task;
import com.example.TrelloClone.Repo.TaskDeleteRepository;
import com.example.TrelloClone.Repo.ModifyRepository;
import com.example.TrelloClone.Repo.TaskRepository;
import com.example.TrelloClone.Repo.UserRepository;
import com.example.TrelloClone.Services.MainService;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;

@Service
public class MainServiceImpl implements MainService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskDeleteRepository taskDeleteRepository;
    private final ModifyRepository modifyRepository;

    public MainServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskDeleteRepository taskDeleteRepository, ModifyRepository modifyRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskDeleteRepository = taskDeleteRepository;
        this.modifyRepository = modifyRepository;
    }

    @Override
    public String findByName(Long suid) {
        return userRepository.findUserNameBySuid(suid);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Long findtaskID(Long id) {
        return taskDeleteRepository.findTaskId(id);
    }

    @Override
    public void deleteCommentById(Long taskID) {
        taskDeleteRepository.deleteCommentsByTaskId(taskID);
    }

    @Override
    public void deleteById(Long taskID) {
        taskDeleteRepository.deleteTaskById(taskID);
    }

    @Override
    public LocalTime fetchTime(Long id) {
        return modifyRepository.fetchTimestamp(id);
    }

    @Override
    public Long fetchTimeInTodo(Long id) {
        return modifyRepository.fetchDurationInTodo(id);
    }

    @Override
    public Long fetchTimeInDoing(Long id) {
        return modifyRepository.fetchDurationInDoing(id);
    }

    @Override
    public Long fetchTimeInDone(Long id) {
        return modifyRepository.fetchDurationInDone(id);
    }

    @Override
    public void updateTimestamp(Long id, Time time) {
        modifyRepository.updateTaskTimestamp(id, time);
    }

    @Override
    public void timeInTodo(Long id, Long time) {
        modifyRepository.updateTimeInTodo(id, time);
    }

    @Override
    public void timeInDoing(Long id, Long time) {
        modifyRepository.updateTimeInDoing(id, time);
    }

    @Override
    public void timeInDone(Long id, Long time) {
        modifyRepository.updateTimeInDone(id, time);
    }

    @Override
    public void changestate(Long id, String state) {
        modifyRepository.updateTaskState(id, state);
    }

    @Override
    public void changeusername(Long id, Long newsuid) {
        modifyRepository.updateTaskOwner(id, newsuid);
    }

    @Override
    public void changename(Long id, String name) {
        modifyRepository.updateTaskName(id, name);
    }

    @Override
    public State getCurrentState(Long taskid) {
        return modifyRepository.fetchCurrentState(taskid);
    }

    @Override
    public void changedesc(Long id, String desc) {
        modifyRepository.updateTaskDescription(id, desc);
    }

    @Override
    public void addComment(Long id, String comment) {
        modifyRepository.addTaskComment(id, comment);
    }
}
