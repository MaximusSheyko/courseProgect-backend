package com.example.courseprogectbackend.service.implementation;

import com.example.courseprogectbackend.model.Like;
import com.example.courseprogectbackend.repository.LikeRepository;
import com.example.courseprogectbackend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void removeByItemIdAndUserId(Long itemId, Long userId) {
        likeRepository.deleteByItem_IdAndUser_Id(itemId, userId);
    }

    @Override
    public void addToItem(Like like) {
        likeRepository.save(like);
    }

    @Override
    public boolean existsByItemIdAndUserId(long itemId, long userId) {
        return likeRepository.existsByItem_IdAndUser_Id(itemId, userId);
    }
}
