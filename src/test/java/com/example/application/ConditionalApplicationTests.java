package com.example.application;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {

    private static GenericContainer<?> devContainer;
    private static GenericContainer<?> prodContainer;

    @Autowired
    TestRestTemplate restTemplate;


    @BeforeAll
    public static void setUp() {
        devContainer = new GenericContainer<>("devapp")
                .withExposedPorts(8080);
        devContainer.start();
        prodContainer = new GenericContainer<>("prodapp")
                .withExposedPorts(8081);
        prodContainer.start();
    }

    @Test
    void contextLoads() {
        Integer port = devContainer.getMappedPort(8080);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "profile/", String.class);

        Assertions.assertEquals("Профиль dev", response.getBody());

    }

    @Test
    void testProdProfile() {
        Integer port = prodContainer.getMappedPort(8081);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "profile/", String.class);

        Assertions.assertEquals("Профиль production", response.getBody());
    }

}
