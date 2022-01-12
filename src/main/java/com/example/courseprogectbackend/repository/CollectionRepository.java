package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Collection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

//    @Query(value = "SELECT collection_id " +
//            "FROM items " +
//            "GROUP BY collection_id " +
//            "ORDER BY collection_id DESC LIMIT :limit", nativeQuery = true)
    @Query("SELECT c FROM Collection c ORDER BY c.items.size DESC")
    Optional<List<Collection>> findCollectionsIdWithMostItems(Pageable pageable);

    boolean existsByIdAndItems_NameContains(long id, String itemName);
}
