package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.Util.DateRecorder;
import com.example.courseprogectbackend.model.Comment;
import com.example.courseprogectbackend.repository.CommentRepository;
import com.example.courseprogectbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final DateRecorder dateRecorder;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, DateRecorder dateRecorder) {
        this.commentRepository = commentRepository;
        this.dateRecorder = dateRecorder;
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<Comment> save(Comment comment) {
        return Optional.of(commentRepository.save(comment));
    }

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByItemId(long itemId) {
        return commentRepository.findAllByItem_Id(itemId);
    }

    @Override
    public boolean exists(long id) {
        return commentRepository.existsById(id);
    }

    @Override
    public LocalDateTime putDateSending(Comment comment) {
        return dateRecorder.currentTime();
    }

    @Override
    public Optional<Comment> findByid(long id) {
        return commentRepository.findById(id);
    }
}
