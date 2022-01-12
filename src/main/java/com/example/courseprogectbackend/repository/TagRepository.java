package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO tags_items VALUES (:tag_id, :item_id)" ,nativeQuery = true)
    int addTagToItemByTagIdAndUserId(@Param("tag_id") long tagId, @Param("item_id") long itemId);
}

