package com.waracle.cakemanager;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CakemanagerApplicationTests {

    private static final String CAKE_JSON = "{\"title\":\"lemon drizzle\",\"description\":\"tasty cake\",\"imageUrl\":\"/cake-photos/1/lemondrizzle.jpeg\"}";
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
    }

    @Test
    public void postAndThenGet() throws JSONException {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        parameters.add("title", "lemon drizzle");
        parameters.add("description", "tasty cake");
        parameters.add("image", new org.springframework.core.io.ClassPathResource("lemondrizzle.jpeg"));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(parameters, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/cakes"), HttpMethod.POST, entity, String.class);

        // Expect Ok
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //Perform HTTP GET

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> getEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> getResponse = restTemplate.exchange(
                createURLWithPort("/cakes"),
                HttpMethod.GET, entity, String.class);

        // Expect Ok
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        //expect a json array with one entry
        JSONAssert.assertEquals("[" + CAKE_JSON + "]", getResponse.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
