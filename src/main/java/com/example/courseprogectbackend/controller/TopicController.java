package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.TopicDto;
import com.example.courseprogectbackend.mapper.TopicMapper;
import com.example.courseprogectbackend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    private final TopicMapper topicMapper;

    @Autowired
    public TopicController(TopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @GetMapping()
    public Set<TopicDto> topics() {
        return topicMapper.toTopicsDto(topicService.getTopics());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK, reason = "Topic save success")
    public void create(@RequestBody TopicDto dto){
        if (topicService.exists(dto.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic is already created");
        }else {
            topicService.save(topicMapper.toTopic(dto));
        }
    }
}
