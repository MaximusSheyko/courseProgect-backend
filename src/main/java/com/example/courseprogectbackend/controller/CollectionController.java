package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.CollectionDto;
import com.example.courseprogectbackend.mapper.CollectionMapper;
import com.example.courseprogectbackend.service.CollectionService;
import com.example.courseprogectbackend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;

    private final CollectionMapper collectionMapper;

    private final TopicService topicService;

    @Autowired
    public CollectionController(CollectionService collectionService,CollectionMapper collectionMapper,
                                TopicService topicService) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
        this.topicService = topicService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CollectionDto> collections() {
        System.out.println(((UserDetails)SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal()).getUsername() + "<".repeat(10));
        return collectionMapper.toCollectionsDto(collectionService.getCollections());
    }

    @GetMapping("/with-most-items")
    public List<CollectionDto>  collectionWithMostItems(@RequestParam("limit") int limit){
        return collectionMapper.toCollectionsDto(collectionService.getCollectionsWithMostItems(limit));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void delete(@RequestParam("id") long id) {
        if (collectionService.exists(id)) {
            collectionService.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection not found");
        }
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(value = HttpStatus.OK, reason = "collection update success")
    public void edit(@RequestParam("id") long id,@RequestBody CollectionDto collectionDto){
        var collection = collectionService.findById(id);
        if (collection.isPresent()){
            var topic = topicService.findByTitle(collectionDto.getTopic());
            var collectionRequest = collectionMapper.toCollection(collectionDto);
            topic.ifPresent(collectionRequest::setTopic);
            collectionService.save(collectionMapper.mergeCollections(collectionRequest, collection.get()));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection  not found");
        }
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void save(@RequestBody CollectionDto dto) {
        var collection = collectionMapper.toCollection(dto);
        var topic = topicService.findByTitle(dto.getTopic());
        topic.ifPresent(collection::setTopic);
        if (collectionService.save(collection).isEmpty()){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Collection is not save");
        }
    }
}
