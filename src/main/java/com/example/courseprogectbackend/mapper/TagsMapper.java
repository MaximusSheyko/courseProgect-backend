package com.example.courseprogectbackend.mapper;

import com.example.courseprogectbackend.dto.TagDto;
import com.example.courseprogectbackend.model.Tag;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagsMapper {

    Set<TagDto> ToTagsDto(Set<Tag> tags);

    Set<Tag> toTags(Set<TagDto> tagsDto);
}
