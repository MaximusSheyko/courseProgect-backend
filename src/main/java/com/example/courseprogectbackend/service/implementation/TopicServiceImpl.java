package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.Topic;
import com.example.courseprogectbackend.repository.TopicRepository;
import com.example.courseprogectbackend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<List<Topic>> getTopics() {
        return Optional.of(topicRepository.findAll());
    }

    @Override
    public boolean exists(String title) {
        return topicRepository.existsTopicByTitle(title);
    }

    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public Optional<Topic> findByTitle(String title) {
        return topicRepository.findByTitle(title);
    }

}
