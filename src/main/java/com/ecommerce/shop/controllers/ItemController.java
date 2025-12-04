package com.ecommerce.shop.controllers;

import com.ecommerce.shop.contracts.ItemCreationRequest;
import com.ecommerce.shop.contracts.ItemResponse;
import com.ecommerce.shop.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/items")
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/{itemId}")
    public ItemResponse GetItem(@PathVariable int itemId) {

        logger.atInfo()
                .addKeyValue("item_id", itemId)
                .log("Get item with id {}.", itemId);

        var response = itemRepository.findById(itemId);

        if (response == null) {
            return null;
        }

        return response.toResponse();
    }

  @PostMapping("/")
  public ItemResponse CreateItem(@RequestBody ItemCreationRequest itemCreationRequest) {
    var item = itemRepository.save(itemCreationRequest.toEntity());

    return item.toResponse();
    }
}

