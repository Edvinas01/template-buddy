package com.edd.rest.builder;

import com.edd.rest.api.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class BuddyBuilder {

    public static PathDecider baseUrl(String baseUrl) {

        return new PathDecider() {

            @Override
            public SimpleRequest path(String path, Object... urlVariables) {
                return null;
            }
        };
    }
}
