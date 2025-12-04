package com.ecommerce.shop.repositories;

import com.ecommerce.shop.entities.ItemEntity;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

    @Observed(
            name = "com.ecommerce.shop.repositories.ItemRepository.findById", // The base name for the metric and span
            contextualName = "findById", // A more descriptive name for the trace span
            lowCardinalityKeyValues = {"item-method", "getter-by-id"} // Tags for metrics and traces
    )
    ItemEntity findById(long id);
}
