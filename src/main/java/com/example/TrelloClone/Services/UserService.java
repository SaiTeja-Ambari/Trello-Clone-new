package com.example.TrelloClone.Services;

import com.example.TrelloClone.Helpers.User;
import com.example.TrelloClone.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // Indicating that this class is a service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository;

    /**
     * Save a user to the repository.
     *
     * @param user The user entity to be saved.
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Fetch all users from the repository.
     *
     * @return an Iterable containing all users.
     */
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
