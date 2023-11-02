package com.example.TrelloClone.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TrelloClone.Helpers.State;
import com.example.TrelloClone.Helpers.Task;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Repository interface for operations related to modifying tasks.
 */
public interface ModifyRepository extends Repository<Task, Long> {

    @Query(value = "SELECT timestamp FROM task WHERE taskid=:id", nativeQuery = true)
    LocalTime fetchTimestamp(Long id);

    @Query(value = "SELECT time_in_todo FROM task WHERE taskid=:id", nativeQuery = true)
    Long fetchDurationInTodo(Long id);

    @Query(value = "SELECT time_in_doing FROM task WHERE taskid=:id", nativeQuery = true)
    Long fetchDurationInDoing(Long id);

    @Query(value = "SELECT time_in_done FROM task WHERE taskid=:id", nativeQuery = true)
    Long fetchDurationInDone(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET timestamp=:time WHERE taskid=:id", nativeQuery = true)
    void updateTaskTimestamp(Long id, Time time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET completion_time=:time WHERE taskid=:id", nativeQuery = true)
    void updateCompletionTime(Long id, Long time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET time_in_todo=:time WHERE taskid=:id", nativeQuery = true)
    void updateTimeInTodo(Long id, Long time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET time_in_doing=:time WHERE taskid=:id", nativeQuery = true)
    void updateTimeInDoing(Long id, Long time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET time_in_done=:time WHERE taskid=:id", nativeQuery = true)
    void updateTimeInDone(Long id, Long time);

    @Query(value = "SELECT state FROM task WHERE taskid=:id", nativeQuery = true)
    State fetchCurrentState(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET state=:state WHERE taskid=:id", nativeQuery = true)
    void updateTaskState(Long id, int state);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET suid=:newsuid WHERE taskid=:id AND EXISTS (SELECT suid FROM user WHERE id=:newsuid)", nativeQuery = true)
    void updateTaskOwner(@Param("id") Long taskId, @Param("newsuid") Long newUserId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET name=:name WHERE taskid=:id", nativeQuery = true)
    void updateTaskName(Long id, String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET description=:description WHERE taskid=:id", nativeQuery = true)
    void updateTaskDescription(@Param("id") Long id, @Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO task_comments (task_taskid, comments) VALUES (:id, :comment)", nativeQuery = true)
    void addTaskComment(Long id, String comment);
}
