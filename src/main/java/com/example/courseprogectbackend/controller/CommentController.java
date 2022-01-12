package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.CommentDto;
import com.example.courseprogectbackend.mapper.CommentMapper;
import com.example.courseprogectbackend.service.CommentService;
import com.example.courseprogectbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    private final ItemService itemService;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper, ItemService itemService) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.itemService = itemService;
    }

    @GetMapping("/api/comments")
    public List<CommentDto> comments() {
        return commentMapper.toCommentsDto(commentService.getComments()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments no found")));
    }

    @PostMapping("/api/comments/save")
    @ResponseStatus(value = HttpStatus.OK, reason = "topic saving success")
    public void edit (@RequestBody CommentDto commentDto){
        var comment = commentMapper.toComment(commentDto);
        commentService.putDateSending(comment);
        if (commentService.save(comment).isEmpty()){
            throw  new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "topic not saving");
        }
    }

    @GetMapping("/api/comments/by-item-id")
    public List<CommentDto> commentsFromItem(@RequestParam("item_id") long itemId){
        if (itemService.exists(itemId)){
            return commentMapper.toCommentsDto(commentService.getCommentsByItemId(itemId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "comments not found")));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }
    }

    @PostMapping("/api/comments/edit")
    public void edit(@RequestParam("id") long id,@RequestBody CommentDto dto){
        var comment = commentService.findByid(id);
        if (comment.isPresent()){
            commentService.save(commentMapper.mergeCommentFromCommentDto(dto, comment.get()));
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Comment not found");
        }
    }
}
