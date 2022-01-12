package com.example.courseprogectbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private Long itemId;
    private String message;
    private String author;
    private String dateCreation;
}
