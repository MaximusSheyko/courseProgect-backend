package com.example.courseprogectbackend.mapper;

import com.example.courseprogectbackend.dto.TopicDto;
import com.example.courseprogectbackend.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(target = "name", source = "title")
    TopicDto toDto(Topic topic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "collections", ignore = true)
    @Mapping(target = "title", source = "name")
    Topic toTopic(TopicDto dto);

    Set<TopicDto> toTopicsDto(List<Topic> topics);
}
