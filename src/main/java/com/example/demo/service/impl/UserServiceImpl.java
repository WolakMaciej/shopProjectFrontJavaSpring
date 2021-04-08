package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserAuthority;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createNew(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public User getOne(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        user.setId(user.getId());
        userRepository.save(user);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException(username);
        }
        return userDetails;
    }

    public UserDetails findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public UserDetails register(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAuthority(UserAuthority.USER);
        return userRepository.save(newUser);
    }

    public User login(String username, String password) {
        User newUser = userRepository.findByUsername(username);
        if(newUser == null) {
            throw new UsernameNotFoundException(username);
        }

        if(!passwordEncoder.matches(password, newUser.getPassword())) {
            throw new UsernameNotFoundException(username);
        }

        newUser.setToken(UUID.randomUUID().toString());
        return userRepository.save(newUser);
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        user.setToken(null);
        userRepository.save(user);
    }
}
