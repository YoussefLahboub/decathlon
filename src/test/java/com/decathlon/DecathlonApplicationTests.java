package com.decathlon;

import com.decathlon.enums.Status;
import com.decathlon.model.Transaction;
import com.decathlon.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DecathlonApplicationTests {

    @LocalServerPort
    int port;

    TestRestTemplate testRestTemplate;

    @BeforeEach
    void setupRestTemplate() {
        var restTemplate = new RestTemplateBuilder().rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplate);
    }

    @Test
    void should_create_transaction_with_NEW_status() throws IOException, URISyntaxException {
        final String baseUrl = "http://localhost:"+port;
        URI uri = new URI(baseUrl);
       var transaction =  TestUtils.mapJsonToObject(Transaction.class, "transaction1.json");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Transaction> request = new HttpEntity<>(transaction, headers);


        var trs = testRestTemplate.postForEntity(uri+"/createPayment", request, Transaction.class).getBody();
        assertNotNull(trs);
        assertEquals(Status.NEW, trs.getStatus());
    }

}
