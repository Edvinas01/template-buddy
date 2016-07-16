package com.edd.rest.api;

import com.edd.rest.api.decider.MethodDecider;
import com.edd.rest.api.executor.ConcreteRequestExecutor;

/**
 * Intermediate interface with a set response type, which allows the selection of request method or a concrete
 * http request execution.
 *
 * @param <T> excepted response type.
 */
public interface TypedRequest<T> extends ConcreteRequestExecutor<T>,
        MethodDecider<T> {
}