package com.edd.rest.api.executor;

import org.springframework.http.ResponseEntity;

/**
 * Executes generic http requests that require a {@link org.springframework.http.HttpMethod} reference.
 *
 * @param <T> response type.
 */
public interface GenericRequestExecutor<T> {

    /**
     * Execute a http request and get the response entity.
     *
     * @return response entity.
     */
    ResponseEntity<T> execute();

    /**
     * Execute a http request and get response object.
     *
     * @return response object.
     */
    default T executeForObject() {
        return execute().getBody();
    }
}