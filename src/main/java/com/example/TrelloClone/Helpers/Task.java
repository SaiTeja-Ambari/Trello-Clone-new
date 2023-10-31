package com.example.TrelloClone.Helpers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskID;

    private Long suid;
    
    @Enumerated(EnumType.STRING) // Assuming State is an enum
    private State state;

    private String name;

    private String description;

    private LocalTime timestamp;

    private Long timeInTodo;

    private Long timeInDoing;

    private Long timeInDone;

    private Long completionTime;

    @ElementCollection(targetClass = String.class)
    private List<String> comments = new ArrayList<>();

    public Task() { 
        // default constructor for JPA
    }

    public Task(Long suid, State state, String name, String description) {
        this.suid = suid;
        this.state = state;
        this.name = name;
        this.description = description;
        this.timestamp = LocalTime.now();
    }

    // Getters and setters

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long suid) {
        this.suid = suid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public Long getTimeInTodo() {
        return timeInTodo;
    }

    public void setTimeInTodo(Long timeInTodo) {
        this.timeInTodo = timeInTodo;
    }

    public Long getTimeInDoing() {
        return timeInDoing;
    }

    public void setTimeInDoing(Long timeInDoing) {
        this.timeInDoing = timeInDoing;
    }

    public Long getTimeInDone() {
        return timeInDone;
    }

    public void setTimeInDone(Long timeInDone) {
        this.timeInDone = timeInDone;
    }

    public Long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void setTimestamp() {
        this.timestamp = LocalTime.now();
    }

}
