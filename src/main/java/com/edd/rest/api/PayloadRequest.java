package com.edd.rest.api;

import com.edd.rest.api.decider.MethodDecider;
import com.edd.rest.api.decider.ResponseTypeDecider;
import com.edd.rest.api.executor.ConcreteRequestExecutor;

/**
 * Intermediate interface which after selecting a payload allows to either execute concrete http request, or to
 * continue building the request by providing a request method.
 */
public interface PayloadRequest extends ResponseTypeDecider,
        ConcreteRequestExecutor,
        MethodDecider {
}