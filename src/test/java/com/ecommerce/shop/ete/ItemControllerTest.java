package com.ecommerce.shop.ete;

import com.ecommerce.shop.configurations.TestcontainersConfiguration;
import com.ecommerce.shop.contracts.ItemCreationRequest;
import com.ecommerce.shop.contracts.ItemResponse;
import com.ecommerce.shop.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public class ItemControllerTest {

  @LocalServerPort private int port;

  @Autowired private ItemRepository itemRepository;

  private RestTestClient client;

  @BeforeEach
  void setUp() {
    itemRepository.deleteAll();

    client = RestTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
  }

  @Test
  void shouldCreateAndRetrieveAnItem() {
    var itemCreationRequest = new ItemCreationRequest("Shoe", "Its a shoe");

    client.post().uri("/items/").body(itemCreationRequest).exchange().expectStatus().isOk();

    var entity =
        new ItemResponse(1L, itemCreationRequest.name(), itemCreationRequest.description());

    client
        .get()
        .uri("/items/1")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(ItemResponse.class)
        .isEqualTo(entity);
  }
}
