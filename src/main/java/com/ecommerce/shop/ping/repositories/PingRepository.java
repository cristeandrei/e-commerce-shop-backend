package com.ecommerce.shop.ping.repositories;

import com.ecommerce.shop.ping.entities.PingEntity;
import org.springframework.data.repository.CrudRepository;

public interface PingRepository extends CrudRepository<PingEntity, Long> {
    PingEntity findById(long id);
}
