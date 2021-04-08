package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        if (CollectionUtils.isEmpty(users)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
        User newUser = userService.createNew(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = userService.getOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User newUser = userService.getOne(id);
        newUser.setUsername(user.getUsername());
       // newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        userService.update(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public UserDetails register(@Valid @RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/details")
    public UserDetails details() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password).getToken();
    }

    @PostMapping("/logout")
    public boolean logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.logout(userDetails.getUsername());
        return true;
    }
}
