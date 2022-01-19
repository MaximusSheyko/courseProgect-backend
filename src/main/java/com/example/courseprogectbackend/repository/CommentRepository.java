package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByItem_Id(Long itemId);
}
