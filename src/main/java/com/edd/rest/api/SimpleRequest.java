package com.edd.rest.api;

/**
 * This interface describes not yet prepared, initial request.
 */
public interface SimpleRequest extends
        ExpectationDecider,
        ConcreteRequestExecutor<Object>,
        MethodDecider<Object> {

    /**
     * Add query param to request.
     *
     * @param name  parameter name.
     * @param value parameter value.
     * @return updated simple request.
     */
    SimpleRequest param(String name, Object value);

    /**
     * Set request payload.
     *
     * @param payload request payload.
     * @return request with added payload.
     */
    PayloadRequest payload(Object payload);
}
