package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.TagDto;
import com.example.courseprogectbackend.mapper.TagsMapper;
import com.example.courseprogectbackend.service.TagService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    private final TagsMapper tagsMapper;

    public TagController(TagService tagService, TagsMapper tagsMapper) {
        this.tagService = tagService;
        this.tagsMapper = tagsMapper;
    }

    @GetMapping()
    public Set<TagDto> tags(){
        return tagsMapper.ToTagsDto(tagService.getTags());
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestParam("item_id") long itemId,@RequestParam("user_id") long userId){
        try{
            tagService.addTagToItemByItemIdAndUserId(itemId, userId);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag already exists that item");
        }
    }
}
