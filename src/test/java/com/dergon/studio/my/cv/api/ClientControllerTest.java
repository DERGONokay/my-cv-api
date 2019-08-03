package com.dergon.studio.my.cv.api;

import com.dergon.studio.my.cv.api.controllers.client.dto.ClientRequest;
import com.dergon.studio.my.cv.api.controllers.client.dto.ClientResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void should_create_client() {
        HttpEntity<ClientRequest> request = new HttpEntity<>(new ClientRequest("foo@gmail.com"), headers);


        ResponseEntity<ClientResponse> response = restTemplate.postForEntity(createURLWithPort("/api/v1/clients/create"), request, ClientResponse.class, new HashMap<String, String>());

        ClientResponse responseBody = response.getBody();

        assertThat(responseBody.getClient()).isNotNull();
        assertThat(responseBody.getClient()).isNotEqualTo("");
        assertThat(responseBody.isCreated()).isTrue();
        assertThat(responseBody.getError()).isNullOrEmpty();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
