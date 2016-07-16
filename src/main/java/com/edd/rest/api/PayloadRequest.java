package com.edd.rest.api;

/**
 * This interface describes a request which has a payload.
 */
public interface PayloadRequest extends ExpectationDecider,
        ConcreteRequestExecutor<Object>,
        MethodDecider<Object> {

}
