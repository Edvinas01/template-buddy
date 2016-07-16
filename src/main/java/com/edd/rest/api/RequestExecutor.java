package com.edd.rest.api;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Interface which describes a generic http request execution.
 *
 * @param <T> response type.
 */
public interface RequestExecutor<T> {

    /**
     * Execute a generic http request and get response entity.
     *
     * @return response entity.
     */
    ResponseEntity<T> executeForEntity();

    /**
     * Execute a generic http request and get response object.
     *
     * @return response object optional.
     */
    default Optional<T> execute() {
        return Optional.ofNullable(executeForEntity().getBody());
    }
}
