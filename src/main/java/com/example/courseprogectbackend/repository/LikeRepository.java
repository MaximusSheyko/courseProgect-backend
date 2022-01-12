package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.Like;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface LikeRepository extends CrudRepository<Like, Long> {

    @Transactional
    void deleteByItem_IdAndUser_Id(long itemId,long userId);

    boolean existsByItem_IdAndUser_Id(long itemId,long userId);
}
