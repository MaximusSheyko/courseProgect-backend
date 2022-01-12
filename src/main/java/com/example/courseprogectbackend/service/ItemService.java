package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Item;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    void deleteById(Long id);

    Optional<List<Item>> getItems();

    void save (@NonNull Item item);

    Optional<Item> findById(long id);

    Optional<List<Item>> findItemsByCollectionId(long id);

    List<Item> findItemsByTagName(@NonNull String name);

    boolean exists(long id);

    boolean exists(long id, String tagName);

    Optional<List<Item>> findItemsOrderByDateCreationByLimit(long limit);

    void recordDateCreationItem(@NonNull Item item);
}
