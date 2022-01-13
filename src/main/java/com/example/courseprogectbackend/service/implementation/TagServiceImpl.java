package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.model.Tag;
import com.example.courseprogectbackend.repository.TagRepository;
import com.example.courseprogectbackend.service.TagService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Optional<Integer> addTagToItemByItemIdAndUserId(long itemId,long userId) {
        return Optional.of(tagRepository.addTagToItemByTagIdAndUserId(itemId, userId));
    }

    @Override
    public Optional<Set<Tag>> getTags() {
        return Optional.of(new HashSet<>(tagRepository.findAll()));
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
