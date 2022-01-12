package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Like;

public interface LikeService {

    void removeByItemIdAndUserId(Long itemId,Long userId);

    void addToItem(Like like);

    boolean existsByItemIdAndUserId(long itemId,long userId);
}
