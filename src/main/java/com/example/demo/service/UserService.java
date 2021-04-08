package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User createNew(User user);

    User getOne(long id);

    void delete(long id);

    void update(User user);

    UserDetails findByToken(String token);

    UserDetails register(User user);

    User login(String username, String password);

    void logout(String username);
}
