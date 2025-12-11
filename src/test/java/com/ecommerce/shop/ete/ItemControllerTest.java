package com.ecommerce.shop.ete;

import com.ecommerce.shop.configurations.TestcontainersConfiguration;
import com.ecommerce.shop.contracts.ItemCreationRequest;
import com.ecommerce.shop.contracts.ItemResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public class ItemControllerTest {

  @LocalServerPort private int port;

  private RestTestClient client;

  @BeforeEach
  void setUp() {
    client = RestTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
  }

  @Test
  void shouldCreateAndRetrieveAnItem() {
    var itemCreationRequest = new ItemCreationRequest("Shoe", "Its a shoe");

    var exchangeResult =
        client
            .get()
            .uri("/login/")
            .headers(e -> e.setBasicAuth("user", "password"))
            .exchange()
            .returnResult();

    var itemResponse =
        client
            .post()
            .uri("/items/")
            .body(itemCreationRequest)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(ItemResponse.class)
            .getResponseBody();

    client
        .get()
        .uri("/items/" + itemResponse.id())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(ItemResponse.class)
        .isEqualTo(itemResponse);
  }
}
