package com.edd.rest.buddy;

import com.edd.rest.api.PathDecider;
import com.edd.rest.api.RequestExecutor;
import com.edd.rest.api.SimpleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class TemplateBuddy extends RestTemplate {

    public static PathDecider baseUrl(String baseUrl) {

        return new PathDecider() {

            @Override
            public SimpleRequest path(String path, Object... urlVariables) {
                return null;
            }
        };
    }
}
