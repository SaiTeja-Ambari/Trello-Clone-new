package com.example.TrelloClone.Services;

import com.example.TrelloClone.Helpers.History;
import com.example.TrelloClone.Repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    /**
     * Save a history to the repository.
     *
     * @param history The history entity to be saved.
     */
    public void saveHistory(History history) {
        historyRepository.save(history);
    }
}
