package com.edd.rest.api;

import com.edd.rest.api.decider.MethodDecider;
import com.edd.rest.api.decider.ResponseTypeDecider;
import com.edd.rest.api.executor.ConcreteRequestExecutor;

/**
 * Initial request which allows configuration of initial properties.
 */
public interface Request extends
        ResponseTypeDecider,
        ConcreteRequestExecutor,
        MethodDecider {

    /**
     * Add additional path.
     *
     * @param path         url path.
     * @param uriVariables path uri variables.
     * @return updated simple request.
     */
    Request path(String path, Object... uriVariables);

    /**
     * Add query param to request.
     *
     * @param name   nonnull parameter name.
     * @param values parameter value.
     * @return updated simple request.
     */
    Request param(String name, Object... values);

    /**
     * Add header.
     *
     * @param name  nonnull header name.
     * @param value nonnull header value.
     * @return updated simple request.
     */
    Request header(String name, String value);

    /**
     * Set request payload.
     *
     * @param payload request payload.
     * @return request with added payload.
     */
    PayloadRequest payload(Object payload);
}