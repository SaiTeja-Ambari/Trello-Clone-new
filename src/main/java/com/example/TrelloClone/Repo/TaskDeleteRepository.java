package com.example.TrelloClone.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.TrelloClone.Helpers.Task;

/**
 * Repository for delete operations related to tasks and their associated comments.
 */
public interface TaskDeleteRepository extends Repository<Task, Long> {

    /**
     * Finds a task ID based on the given ID.
     * 
     * @param id the ID of the task to find.
     * @return the task ID if found.
     */
    @Query(value = "SELECT u.taskid FROM task u WHERE u.taskid = :id", nativeQuery = true)
    Long findTaskId(Long id);

    /**
     * Deletes comments associated with the given task ID.
     * 
     * @param taskID the ID of the task whose comments should be deleted.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM task_comments t WHERE t.task_taskid = :taskID", nativeQuery = true)
    void deleteCommentsByTaskId(Long taskID);

    /**
     * Deletes a task based on the given task ID.
     * 
     * @param taskID the ID of the task to delete.
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM task t WHERE t.taskid = :taskID", nativeQuery = true)
    void deleteTaskById(Long taskID);
}
