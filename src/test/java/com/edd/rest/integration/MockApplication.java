package com.edd.rest.integration;

import com.edd.rest.TemplateBuddy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@SpringBootApplication
@SuppressWarnings("MVCPathVariableInspection")
public class MockApplication {

    private static final List<String> HEADER_VALUES = Arrays.asList("one", "two");

    /**
     * Controller to test out if global and url configurations do work.
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public void configController(@RequestHeader List<String> headers) {
        Assert.isTrue(headers.containsAll(HEADER_VALUES));
    }

    /**
     * Share same template buddy bean in the whole application.
     */
    @Bean
    public TemplateBuddy templateBuddy() {
        return new TemplateBuddy(TemplateBuddy
                .configure()

                // Global configuration.
                .configuration(TemplateBuddy
                        .configuration()
                        .header("headers", HEADER_VALUES.get(0))
                        .build())

                // Configure headers for /test endpoint.
                .configurationFor("http://**/config", TemplateBuddy
                        .configuration()

                        // todo send only timeout config to factory, make separate config for general settings
                        .header("headers", HEADER_VALUES.get(1))
                        .build())
                .toFactory());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MockApplication.class, args);
    }
}