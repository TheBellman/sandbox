package net.parttimepolymath.sandbox.springboot.web;

import net.parttimepolymath.sandbox.springboot.model.VersionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VersionControllerTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("http://localhost:%d/api", port);
        restTemplate = new RestTemplate();
    }

    @Test
    void testVersion() {
        ResponseEntity<VersionResponse> response = restTemplate.getForEntity(baseUrl + "/version",
                VersionResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("springboot", response.getBody().getName());

        ResponseEntity<VersionResponse> other = restTemplate.getForEntity(baseUrl, VersionResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response, other);
    }

}