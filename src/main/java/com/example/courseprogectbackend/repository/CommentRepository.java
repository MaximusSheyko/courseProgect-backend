package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByItem_Id(Long itemId);
}
