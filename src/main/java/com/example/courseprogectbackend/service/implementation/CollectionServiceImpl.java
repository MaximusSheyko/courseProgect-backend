package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.Collection;
import com.example.courseprogectbackend.repository.CollectionRepository;
import com.example.courseprogectbackend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public List<Collection> getCollections() {
        return collectionRepository.findAll();
    }

    @Override
    public List<Collection> getCollectionsWithMostItems(int limit) {
        return collectionRepository.findCollectionsIdWithMostItems(PageRequest.ofSize(limit));
    }

    @Override
    public void deleteById(long id) {
        collectionRepository.deleteById(id);
    }

    @Override
    public Optional<Collection> findById(long id) {
        return collectionRepository.findById(id);
    }

    @Override
    public Optional<Collection> save(@NonNull Collection collection) {
        return Optional.of(collectionRepository.save(collection));
    }

    @Override
    public boolean exists(long id) {
        return collectionRepository.existsById(id);
    }

    @Override
    public boolean exists(long id, String itemName) {
        return collectionRepository.existsByIdAndItems_NameContains(id, itemName);
    }
}
