package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM items ORDER BY date_creation DESC LIMIT :limit", nativeQuery = true)
    List<Item> findAllOrderByDateCreationByLimit(@Param("limit") long limit);

    List<Item> findAllByCollection_Id(long Id);

    boolean existsByNameAndCollection_Id(String name,Long collectionId);

    boolean existsByIdAndTags_NameContains(Long id,String tagsName);

    Optional<List<Item>> findAllByTags_NameContains(String tags_name);
}
