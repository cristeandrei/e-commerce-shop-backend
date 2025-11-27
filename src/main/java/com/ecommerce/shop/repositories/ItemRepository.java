package com.ecommerce.shop.repositories;

import com.ecommerce.shop.entities.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    ItemEntity findById(long id);
}
