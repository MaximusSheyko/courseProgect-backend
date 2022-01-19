package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/exists")
    public Boolean exists(@RequestParam("item_id") long itemId, @RequestParam("user_id") long userId){
        return likeService.existsByItemIdAndUserId(itemId, userId);
    }

    @DeleteMapping ("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(value = HttpStatus.OK, reason = "like deleted")
    public void delete(@RequestParam("item_id") long itemId, @RequestParam("user_id") long userId){
        if (likeService.existsByItemIdAndUserId(itemId, userId )){
            likeService.removeByItemIdAndUserId(itemId, userId);
        }else {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "User has not yet rated item");
        }
    }
}
