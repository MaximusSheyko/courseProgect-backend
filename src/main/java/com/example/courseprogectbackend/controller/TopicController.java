package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.TopicDto;
import com.example.courseprogectbackend.mapper.TopicMapper;
import com.example.courseprogectbackend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class TopicController {

    private final TopicService topicService;

    private final TopicMapper topicMapper;

    @Autowired
    public TopicController(TopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @GetMapping("/api/topics")
    public Set<TopicDto> topics() {
        return topicMapper.toTopicsDto(topicService.getTopics()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topics not found")));
    }

    @PostMapping("/api/topics/create")
    @ResponseStatus(value = HttpStatus.OK, reason = "Topic save success")
    public void create(@RequestBody TopicDto dto){
        if (topicService.exists(dto.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Topic is already created");
        }else {
            topicService.save(topicMapper.toTopic(dto));
        }
    }
}
