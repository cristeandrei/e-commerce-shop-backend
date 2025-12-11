package com.ecommerce.shop.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id private String username;

  private String password;

  private Boolean enabled;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<AuthorityEntity> authorities;
}
