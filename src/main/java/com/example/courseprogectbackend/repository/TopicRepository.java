package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsTopicByTitle(String title);

    Optional<Topic> findByTitle(String title);
}
