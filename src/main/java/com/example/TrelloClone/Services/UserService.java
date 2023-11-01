package com.example.TrelloClone.Services;

import com.example.TrelloClone.Helpers.User;

public interface UserService {
    void save(User user);
    Iterable<User> getAllUsers();
}
