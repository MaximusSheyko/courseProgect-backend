package com.example.courseprogectbackend.mapper;

import com.example.courseprogectbackend.dto.ItemDto;
import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.model.Tag;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {TagsMapper.class})
public abstract class ItemMapper {

    private static final String FORMAT_DATE = "dd.MM.yyyy HH:mm:ss";

    @Mapping(target = "dateCreation", dateFormat = FORMAT_DATE)
    public abstract ItemDto toDto(Item item);

    @Mapping(target = "collection", ignore = true)
    @Mapping( target = "likeList", ignore = true)
    @Mapping( target = "comments", ignore = true)
    public abstract Item toItem(ItemDto dto);

    public abstract List<Item> toItems(List<ItemDto> itemsDto);

    public abstract List<ItemDto> toItemsDto(List<Item> itemsDto);

    public Item mergeItems(Item source, @NonNull Item target){
        if (Objects.nonNull(source)){
            if (Objects.nonNull(source.getTags()) && !source.getTags().isEmpty()){
                target.setTags(mergeTags(source.getTags(), target.getTags()));
            }
        }
        return target;
    }

    private Set<Tag> mergeTags(Set<Tag> tagsSource, Set<Tag> tagsTarget){
        if (Objects.nonNull(tagsTarget) && !tagsTarget.isEmpty()){
            tagsTarget.addAll(tagsSource);
            return tagsTarget;
        }else {
            return tagsSource;
        }
    }
}
