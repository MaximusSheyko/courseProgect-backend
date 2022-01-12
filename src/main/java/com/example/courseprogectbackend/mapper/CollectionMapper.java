package com.example.courseprogectbackend.mapper;

import com.example.courseprogectbackend.dto.CollectionDto;
import com.example.courseprogectbackend.model.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TopicMapper.class})
public interface CollectionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "topic", source = "topic.title")
    @Mapping(target = "imageHeadUrl", source = "imageHeadUrl")
    CollectionDto toCollectionDto(Collection collection);


    @Mapping(target = "items", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "topic.title", source = "topic")
    Collection toCollection(CollectionDto collection);

    List<CollectionDto> toCollectionsDto(List<Collection> collections);

    List<Collection> toCollections(List<CollectionDto> collectionsDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "user", ignore = true)
    Collection mergeCollections(Collection source,@MappingTarget Collection target);
}
