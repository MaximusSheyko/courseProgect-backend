package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/api/likes/exists")
    public Boolean exists(@RequestParam("item_id") long itemId, @RequestParam("user_id") long userId){
        return likeService.existsByItemIdAndUserId(itemId, userId);
    }

    @DeleteMapping ("/api/likes/delete")
    @ResponseStatus(value = HttpStatus.OK, reason = "like deleted")
    public void delete(@RequestParam("item_id") long itemId, @RequestParam("user_id") long userId){
        if (likeService.existsByItemIdAndUserId(itemId, userId )){
            likeService.removeByItemIdAndUserId(itemId, userId);
        }else {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User has not yet rated item");
        }

    }

}
