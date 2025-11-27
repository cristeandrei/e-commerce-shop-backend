package com.ecommerce.shop.contracts;

import org.jspecify.annotations.Nullable;

public record ItemResponse(Long id, String name, @Nullable String description) {
}
