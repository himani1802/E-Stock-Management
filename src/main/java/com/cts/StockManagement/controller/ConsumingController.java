package com.cts.StockManagement.controller;

import com.cts.StockManagement.model.UserDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@SecurityRequirements
@RequestMapping("/consumer/")
public class ConsumingController {
    @PostMapping("getLogin")
    public ResponseEntity<?> consumeLogin(@RequestBody UserDTO user) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8081/auth/v1/user/login";
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(user), String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
    }

    private static HttpEntity<?> getHeaders(UserDTO user) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(user, headers);
    }
}
