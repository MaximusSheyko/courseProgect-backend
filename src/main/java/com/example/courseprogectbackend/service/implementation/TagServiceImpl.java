package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.Tag;
import com.example.courseprogectbackend.repository.TagRepository;
import com.example.courseprogectbackend.service.TagService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Optional<Integer> addTagToItemByItemIdAndUserId(long itemId, long userId) {
        return Optional.of(tagRepository.addTagToItemByTagIdAndUserId(itemId, userId));
    }

    @Override
    public Set<Tag> getTags() {
        return new HashSet<>(tagRepository.findAll());
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
