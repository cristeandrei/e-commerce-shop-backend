package com.ecommerce.shop.entities;

import com.ecommerce.shop.contracts.ItemResponse;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_generator")
  @SequenceGenerator(name = "item_id_generator", sequenceName = "item_id_seq")
  private Long id;

  private String name;

  private String description = "";

  public ItemEntity(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public ItemEntity() {}

  public ItemResponse toResponse() {
    return new ItemResponse(id, name, description);
  }
}
