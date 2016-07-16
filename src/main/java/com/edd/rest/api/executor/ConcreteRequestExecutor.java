package com.edd.rest.api.executor;

import org.springframework.http.ResponseEntity;

/**
 * Executes concrete http requests, without the need of providing http method.
 *
 * @param <T> response object type.
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
     * @return object optional.
     */
    default T post() {
        return postForEntity().getBody();
    }

    /**
     * Perform http get request and extract the response object.
     *
     * @return response object.
     */
    default T get() {
        return getForEntity().getBody();
    }

    /**
     * Perform http delete request and extract response object.
     *
     * @return response object.
     */
    default T delete() {
        return deleteForEntity().getBody();
    }
}