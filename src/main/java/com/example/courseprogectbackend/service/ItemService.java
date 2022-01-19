package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Item;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    void deleteById(Long id);

    List<Item> getItems();

    void save (@NonNull Item item);

    Optional<Item> findById(long id);

    List<Item> findItemsByCollectionId(long id);

    boolean exists(long id);

    boolean exists(long id, String tagName);

    List<Item> findItemsOrderByDateCreationByLimit(long limit);

    void recordDateCreationItem(@NonNull Item item);

    List<Item> findItemsByTagName(@NonNull String name);

    List<Item> searchItemsByQuery(String text,String... fields);
}
