package com.edd.rest;

import com.edd.rest.api.SimpleRequest;
import com.edd.rest.builder.BuddyBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);


        SimpleRequest request = BuddyBuilder.path("");

        request.param("", "")
                .method(HttpMethod.DELETE)
                .execute();

        request.param("", "")
                .postForEntity();

        request.param("", "")
                .payload(null)
                .postForEntity();

        String s = request.param("", "")
                .payload(null)
                .expect(String.class)
                .post()
                .get();

        s = request.param("", "")
                .expect(String.class)
                .postForEntity()
                .getBody();

        request.param("", "")
                .payload(null)
                .method(HttpMethod.DELETE)
                .execute();

        s = request.param("", "")
                .payload(null)
                .expect(String.class)
                .method(HttpMethod.DELETE)
                .execute()
                .get();
    }
}
