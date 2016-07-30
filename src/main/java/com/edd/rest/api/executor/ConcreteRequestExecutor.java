package com.edd.rest.api.executor;

import org.springframework.http.ResponseEntity;

/**
 * Executes concrete http requests, without the need of providing http method.
 *
 * @param <T> response object type.
 */
public interface ConcreteRequestExecutor<T> {

    /**
     * Perform http get request.
     *
     * @return response entity.
     */
    ResponseEntity<T> get();

    /**
     * Perform http post request.
     *
     * @return response entity.
     */
    ResponseEntity<T> post();

    /**
     * Perform http delete request.
     *
     * @return response entity.
     */
    ResponseEntity<T> delete();

    /**
     * Perform http post request and extract the response object.
     *
     * @return object optional.
     */
    default T postForObject() {
        return post().getBody();
    }

    /**
     * Perform http get request and extract the response object.
     *
     * @return response object.
     */
    default T getForObject() {
        return get().getBody();
    }

    /**
     * Perform http delete request and extract response object.
     *
     * @return response object.
     */
    default T deleteForObject() {
        return delete().getBody();
    }
}