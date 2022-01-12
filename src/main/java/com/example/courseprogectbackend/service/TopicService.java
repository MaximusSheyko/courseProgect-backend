package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TopicService {
    Optional<List<Topic>> getTopics();

    boolean exists(String title);

    void save(Topic topic);

    Optional<Topic> findByTitle(String title);
}
