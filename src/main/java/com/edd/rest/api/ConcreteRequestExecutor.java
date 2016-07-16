package com.edd.rest.api;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Interface which describes concrete http request execution.
 *
 * @param <T> response type for requests.
 */
public interface ConcreteRequestExecutor<T> {

    /**
     * Perform http post request.
     *
     * @return response entity.
     */
    ResponseEntity<T> postForEntity();

    /**
     * Perform http get request.
     *
     * @return response entity.
     */
    ResponseEntity<T> getForEntity();

    /**
     * Perform http delete request.
     *
     * @return response entity.
     */
    ResponseEntity<T> deleteForEntity();

    /**
     * Perform http post request and extract the response object.
     *
     * @return response object optional.
     */
    default Optional<T> post() {
        return Optional.ofNullable(postForEntity().getBody());
    }

    /**
     * Perform http get request and extract the response object.
     *
     * @return response object optional.
     */
    default Optional<T> get() {
        return Optional.ofNullable(getForEntity().getBody());
    }

    /**
     * Perform http delete request and extract response object.
     *
     * @return response object optional.
     */
    default Optional<T> delete() {
        return Optional.ofNullable(deleteForEntity().getBody());
    }
}
