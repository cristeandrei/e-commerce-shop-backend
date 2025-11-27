package com.ecommerce.shop.contracts;

import com.ecommerce.shop.entities.ItemEntity;
import org.jspecify.annotations.Nullable;

public record ItemCreationRequest(int id, String name, @Nullable String description) {
    public ItemEntity  toEntity() { return new ItemEntity(null, name, description); }
}
