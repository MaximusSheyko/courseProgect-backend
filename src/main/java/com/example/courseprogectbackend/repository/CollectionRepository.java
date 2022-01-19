package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Collection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("SELECT c FROM Collection c ORDER BY c.items.size DESC")
    List<Collection> findCollectionsIdWithMostItems(Pageable pageable);

    boolean existsByIdAndItems_NameContains(long id, String itemName);
}
