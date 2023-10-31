package com.example.TrelloClone.Controller;

import com.example.TrelloClone.Helpers.User;
import com.example.TrelloClone.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo/users") // Updated URL path to be more descriptive
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Adds a new user.
     *
     * @param suid  - User's ID
     * @param name  - User's name
     * @param email - User's email
     * @return "Saved" if the user was added successfully
     */
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam Long suid, @RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setId(suid);
        user.setName(name);
        user.setEmail(email);
System.out.print(suid);
        userService.save(user);
        return "Saved";
    }

    /**
     * Retrieves all registered users.
     *
     * @return list of all users
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
