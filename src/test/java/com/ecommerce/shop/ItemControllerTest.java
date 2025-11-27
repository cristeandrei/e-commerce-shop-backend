package com.ecommerce.shop;

import com.ecommerce.shop.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(TestcontainersConfiguration.class)
public class ItemControllerTest {
    @Autowired
    private ItemRepository pingRepository;

    @BeforeEach
    void setUp() {
        pingRepository.deleteAll();
    }

    @Test
    void shouldGetAllBookmarks() {
        var webTestClient = RestTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .build();

        webTestClient
                .get()
                .uri("/ping/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello World");

        webTestClient
                .get()
                .uri("/ping/count")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("1");
    }
}
