package com.ecommerce.shop.ping.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ping")
public class PingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ping_id_generator")
    @SequenceGenerator(name = "ping_id_generator", sequenceName = "ping_id_seq")
    private Long id;
}
