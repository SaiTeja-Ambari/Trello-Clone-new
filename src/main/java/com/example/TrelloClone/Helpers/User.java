package com.example.TrelloClone.Helpers;
import javax.persistence.*;

//The user class is a table in the database. It has fields which stores information about the user, including the user's name, email and suID.
@Entity
public class User {
    @Id
    @Column(unique = true)
    private Long id;

    private String name;

    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
