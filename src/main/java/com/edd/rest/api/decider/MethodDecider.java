package com.edd.rest.api.decider;

import com.edd.rest.api.executor.GenericRequestExecutor;
import org.springframework.http.HttpMethod;

/**
 * Makes decision of {@link org.springframework.http.HttpMethod} which is to be used when making the request.
 */
public interface MethodDecider<T> {

    /**
     * Set which http method is to be performed.
     *
     * @param method nonnull http method.
     * @return request executor with set http method.
     */
    GenericRequestExecutor<T> method(HttpMethod method);
}