package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    void deleteById(long id);

    Optional<Comment> save(Comment comment);

    List<Comment> getComments();

    List<Comment> getCommentsByItemId(long itemId);

    Optional<Comment> findByid(long id);

    boolean exists(long itemId);

    LocalDateTime putDateSending(Comment comment);
}
