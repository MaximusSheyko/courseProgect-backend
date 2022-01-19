package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.User;
import com.example.courseprogectbackend.repository.UserRepository;
import com.example.courseprogectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByRoleName(@NonNull String role) {
        return userRepository.findUserByRoles_NameContains(role);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsUserByName(name);
    }

    @Override
    public User findById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

}
