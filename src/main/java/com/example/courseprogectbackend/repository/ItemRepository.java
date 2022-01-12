package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByTags_NameContains(String tagsName);

    @Query(value = "SELECT * FROM items ORDER BY date_creation DESC LIMIT :limit", nativeQuery = true)
    List<Item> findAllOrderByDateCreationByLimit(@Param("limit") long limit);

    List<Item> findAllByCollection_Id(long Id);

    boolean existsByNameAndCollection_Id(String name,Long collectionId);

    boolean existsByIdAndTags_NameContains(Long id,String tagsName);
}
