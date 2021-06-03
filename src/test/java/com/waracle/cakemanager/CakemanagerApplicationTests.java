package com.waracle.cakemanager;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CakemanagerApplicationTests {

	@LocalServerPort
	private int port;

	private static final String CAKE_JSON = "{\"title\":\"new cake\",\"description\":\"tasty cake\",\"imageUrl\":\"t\"}";

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	void contextLoads() {
	}

	@Test
	void postAndThenGet() throws JSONException {

		//Perform HTTP POST

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(CAKE_JSON, headers);

		restTemplate.exchange(
				createURLWithPort("/cakes"),
				HttpMethod.POST, entity, String.class);


		//Perform HTTP GET

		entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/cakes"),
				HttpMethod.GET, entity, String.class);

		//expect a json array with one entry
		JSONAssert.assertEquals("[" + CAKE_JSON + "]", response.getBody(), false);

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
