package com.demo.airline.controllers;

import com.demo.airline.models.Student;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class StudentControllerTest {
    private String baseUrl;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:8085/";
        headers = new HttpHeaders();
        headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void testGetById() {
        //String url = "http://localhost:8085/university/api/v1/student/2";

        long id = 2;
        String url = baseUrl + "/university/api/v1/student/" + id;

        ResponseEntity<Student> response = new RestTemplate().getForEntity(url, Student.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(id, response.getBody().getId());

        String header_airline = "welcome-spring-airline";
        Assertions.assertEquals(header_airline, response.getHeaders().get("spring-airline").get(0));
    }

    @Test
    void testGetById2() {
        long id = 2;
        String url = baseUrl + "/university/api/v1/student/" + id;
        String strObject = new RestTemplate().getForObject(url, String.class);
        System.out.println(strObject);
    }

    @Test
    void testGetOnePathParamXML() {
        // request xml
        HttpHeaders headerXml = new HttpHeaders();
        headerXml.add("accept", MediaType.APPLICATION_XML_VALUE);
        ResponseEntity<String> resXml = new RestTemplate().exchange(
                baseUrl + "/university/api/v1/student/simple?id=2", HttpMethod.GET, new HttpEntity<>(headerXml), String.class);
        System.out.println("response XML status:" + resXml.getStatusCode());
        System.out.println("response XML body:" + resXml.getBody());

        // request json
        HttpHeaders headerJson = new HttpHeaders();
        headerJson.add("accept", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> resJson = new RestTemplate().exchange(baseUrl + "/university/api/v1/student/simple?id=2",
                HttpMethod.GET, new HttpEntity<>(headerJson), String.class);
        System.out.println("response JSON status:" + resJson.getStatusCode());
        System.out.println("response JSON body:" + resJson.getBody());

        // request mixed: xml + json
        HttpHeaders mixedHeaders = new HttpHeaders();
        mixedHeaders.add("accept", "application/json, application/xml");
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(baseUrl + "/university/api/v1/student/simple?id=2",
                HttpMethod.GET, new HttpEntity<>(mixedHeaders), String.class);
        System.out.println("response status:" + responseEntity.getStatusCode());
        System.out.println("response body:" + responseEntity.getBody());
    }
}
