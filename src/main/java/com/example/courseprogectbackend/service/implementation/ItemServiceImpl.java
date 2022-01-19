package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.Util.DateRecorder;
import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.repository.ItemRepository;
import com.example.courseprogectbackend.search.implementation.ItemSearchImpl;
import com.example.courseprogectbackend.service.ItemService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final DateRecorder dateRecorder;

    private final ItemSearchImpl search;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, DateRecorder dateRecorder, ItemSearchImpl search) {
        this.itemRepository = itemRepository;
        this.dateRecorder = dateRecorder;
        this.search = search;
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public void save(@NonNull Item item) {
        itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findItemsByCollectionId(long id) {
        return itemRepository.findAllByCollection_Id(id);
    }

    @Override
    public boolean exists(long id) {
        return itemRepository.existsById(id);
    }

    @Override
    public boolean exists(long id, String tagName) {
        return itemRepository.existsByIdAndTags_NameContains(id, tagName);
    }

    @Override
    public List<Item> findItemsOrderByDateCreationByLimit(long limit) {
        return itemRepository.findAllOrderByDateCreationByLimit(limit);
    }

    @Override
    public void recordDateCreationItem(@NonNull Item item) {
        item.setDateCreation(dateRecorder.currentTime());
    }

    @Override
    public List<Item> findItemsByTagName(String name) {
        return itemRepository.findAllByTags_NameContains(name);
    }

    @Override
    public List<Item> searchItemsByQuery(String text, String...fields) {
        return search.search(text, fields);
    }
}
