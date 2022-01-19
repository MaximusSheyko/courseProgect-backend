package com.example.courseprogectbackend.mapper;

import com.example.courseprogectbackend.dto.CommentDto;
import com.example.courseprogectbackend.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    String FORMAT_DATE = "dd.MM.yyyy HH:mm:ss";

    @Mapping(target = "author", source = "user.name")
    @Mapping( target = "itemId", source = "item.id")
    @Mapping( target = "dateCreation", dateFormat = FORMAT_DATE)
    CommentDto ToCommentDto(Comment comment);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "user.name", source = "author")
    @Mapping( target = "item.id", source = "itemId")
    Comment toComment(CommentDto commentDto);

    List<CommentDto> toCommentsDto(List<Comment> comments);

    @Mapping(target = "item", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment mergeCommentFromCommentDto(CommentDto dto,@MappingTarget Comment comment);
}
