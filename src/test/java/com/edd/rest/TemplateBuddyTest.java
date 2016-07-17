package com.edd.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class TemplateBuddyTest {

    private static final String BASE_URL = "http://localhost:8080";

    private MockRestServiceServer server;
    private TemplateBuddy buddy;

    @Before
    public void setUp() {
        buddy = new TemplateBuddy();
        server = MockRestServiceServer.createServer(buddy);
    }

    @After
    public void tearDown() {
        server.verify();
    }

    @Test
    public void getObject() {
        server.expect(requestTo(String.format("%s/something?p1=1&p2=2", BASE_URL)))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("test", "test"))
                .andRespond(withSuccess("{\"name\":\"tom\", \"age\":1}", MediaType.APPLICATION_JSON));

        Person person = buddy.fromUrl("{url}/something", BASE_URL)
                .param("p1", "1")
                .param("p2", "2")
                .header("test", "test")
                .expect(Person.class)
                .get();

        assertThat(person.getName()).isEqualTo("tom");
        assertThat(person.getAge()).isEqualTo(1);
    }

    @Test
    public void post() {
        server.expect(requestTo(BASE_URL))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));

        String hello = buddy.fromUrl(BASE_URL)
                .expect(String.class)
                .post();

        assertThat(hello).isEqualTo("hello");
    }

    @Test
    public void noResponse() {

        // Setup multiple requests.
        for (HttpMethod method : Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)) {
            server.expect(requestTo(BASE_URL))
                    .andExpect(method(method))
                    .andRespond(withSuccess());
        }

        buddy.fromUrl(BASE_URL).get();
        buddy.fromUrl(BASE_URL).post();
        buddy.fromUrl(BASE_URL).delete();
    }

    @Test
    public void execute() {
        server.expect(requestTo(BASE_URL))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess("{\"name\":\"tom\", \"age\":1}", MediaType.APPLICATION_JSON));

        ResponseEntity entity = buddy.fromUrl(BASE_URL)
                .payload(new Person("tom", 1))
                .method(HttpMethod.PUT)
                .executeForEntity();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Class for testing response type.
     */
    private static class Person {

        private final String name;
        private final int age;

        @JsonCreator
        private Person(@JsonProperty("name") String name,
                       @JsonProperty("age") int age) {

            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}