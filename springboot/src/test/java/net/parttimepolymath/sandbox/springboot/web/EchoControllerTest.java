package net.parttimepolymath.sandbox.springboot.web;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/*
 * This test explicitly switches on the release profile, just so that we can demonstrate the use
 * of different spring profiles. The implementation of EchoService in the controller is, by default,
 * the SimpleEchoService rather than the StoringEchoService. In this test, we want to go through the
 * StoringEchoService. This probably won't work in a real scenario where we have a real database
 * backing the StoringEchoService, but it is sufficient for our purposes.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("release")
class EchoControllerTest {

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
    void testEcho() {
        ResponseEntity<EchoResponse> response = restTemplate.postForEntity(baseUrl + "/echo", new EchoRequest("baz"), EchoResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("baz", response.getBody().getMessage());
    }
}