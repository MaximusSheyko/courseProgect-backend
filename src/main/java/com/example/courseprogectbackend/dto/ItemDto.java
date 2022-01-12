package com.example.courseprogectbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
    private String dateCreation;
    private List<TagDto> tags;

}
