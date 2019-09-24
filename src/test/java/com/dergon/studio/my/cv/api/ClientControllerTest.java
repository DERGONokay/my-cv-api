package com.dergon.studio.my.cv.api;

import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientRequest;
import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    @Before
    public void setUp() {
        restTemplate = new TestRestTemplate();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void should_create_client() {
        HttpEntity<CreateClientRequest> request = new HttpEntity<>(new CreateClientRequest("foo@gmail.com"), headers);

        ResponseEntity<CreateClientResponse> response = restTemplate.postForEntity(TestUtils.createURLWithPort(port, "/api/v1/clients"), request, CreateClientResponse.class, new HashMap<String, String>());

        CreateClientResponse responseBody = response.getBody();

        assertThat(responseBody.getClient()).isNotNull();
        assertThat(responseBody.isCreated()).isTrue();
        assertThat(responseBody.getError()).isNullOrEmpty();
    }

    @Test
    public void should_return_bad_request() {
        HttpEntity<CreateClientRequest> request = new HttpEntity<>(new CreateClientRequest("@gmail.com"), headers);

        ResponseEntity<CreateClientResponse> response = restTemplate.postForEntity(TestUtils.createURLWithPort( port, "/api/v1/clients"), request, CreateClientResponse.class, new HashMap<>());

        CreateClientResponse responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
        assertThat(responseBody.getClient()).isNull();
        assertThat(responseBody.isCreated()).isFalse();
        assertThat(responseBody.getError()).isNotEmpty();
    }
}
