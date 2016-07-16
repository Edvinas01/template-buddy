package com.edd.rest.api;

import org.springframework.http.HttpMethod;

/**
 * Describes http method.
 */
public interface MethodDecider<T> {

    /**
     * Set which http method is to be performed.
     *
     * @param method http method to perform.
     * @return request executor with request method.
     */
    RequestExecutor<T> method(HttpMethod method);
}
