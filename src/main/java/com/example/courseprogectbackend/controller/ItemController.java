package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.ItemDto;
import com.example.courseprogectbackend.mapper.ItemMapper;
import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.service.CollectionService;
import com.example.courseprogectbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
public class ItemController {

    private final ItemService itemService;

    private final CollectionService collectionService;

    private final ItemMapper itemMapper;

    @Autowired
    public ItemController(ItemService itemService,CollectionService collectionService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.collectionService = collectionService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/api/items")
    public List<ItemDto> items(){
        return itemMapper.toItemsDto(itemService.getItems()
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found")));
    }

    @PostMapping("/api/items/save")
    @ResponseStatus(value = HttpStatus.OK, reason = "item saving success")
    public void save(@Param ("collection_id") long collectionId, @RequestBody ItemDto itemDto){
        if (collectionService.exists(collectionId, itemDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Item is already exists in this collection");
        }else {
            var item = itemMapper.toItem(itemDto);
            itemService.recordDateCreationItem(item);
            itemService.save(item);
        }
    }

    @PostMapping("/api/items/edit")
    @ResponseStatus(value = HttpStatus.OK, reason = "success update")
    public void edit(@RequestParam("item_id") long itemId, @RequestBody ItemDto itemDto){
        var itemRequest = itemMapper.toItem(itemDto);
        var item = itemService.findById(itemId);
        if (item.isPresent()){
            itemService.save(itemMapper.mergeItems(itemRequest, item.get()));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found for update");
        }
    }

    @GetMapping("/api/items/order-by-date")
    public List<ItemDto> itemsLimit(@RequestParam("limit") long limit){
        return itemMapper.toItemsDto(itemService.findItemsOrderByDateCreationByLimit(limit)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found")));
    }

    @GetMapping("/api/items/by-collection-id")
    public List<ItemDto> itemsFromCollection(@RequestParam("id") long id){
        if (collectionService.exists(id)){
            return itemMapper.toItemsDto(itemService.findItemsByCollectionId(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Items have not created yet")));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection not found");
        }
    }

    @GetMapping("/api/items/by-tag_name")
    public List<ItemDto> itemsByTagName(@Param("name") String name){
        return itemMapper.toItemsDto(itemService.findItemsByTagName(name)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "items not found by this tag")));
    }
}
