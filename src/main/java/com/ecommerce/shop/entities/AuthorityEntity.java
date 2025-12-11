package com.ecommerce.shop.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String authority;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username", nullable = false)
  private UserEntity user;
}
