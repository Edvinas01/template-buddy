package com.edd.rest.api;

import org.springframework.http.HttpMethod;

/**
 * Describes request which expects a specific response type.
 *
 * @param <T> excepted response type.
 */
public interface TypedRequest<T> extends ConcreteRequestExecutor<T>, MethodDecider<T> {

}
