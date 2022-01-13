package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.model.Tag;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

import java.util.Optional;

public interface TagService {
    Optional<Integer> addTagToItemByItemIdAndUserId(long itemId,long userId);

    Optional<Set<Tag>> getTags();

    void save(Tag tag);
}
