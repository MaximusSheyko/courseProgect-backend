package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Collection;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CollectionService {

    Optional<List<Collection>> getCollections();

    Optional<List<Collection>> getCollectionsWithMostItems(int limit);

    void deleteById(long id);

    Optional<Collection> findById(long id);

    Optional<Collection> save(@NonNull Collection collection);

    boolean exists(long id);

    boolean exists(long id, String itemName);
}
