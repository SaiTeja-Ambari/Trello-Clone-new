package com.example.TrelloClone.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TrelloClone.Helpers.History;

/**
 * Repository interface for CRUD operations related to the History entity.
 */
public interface HistoryRepository extends CrudRepository<History, Long> {
    // Additional custom query methods can be defined here if required.
}
