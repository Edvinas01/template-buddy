package com.edd.rest.api.decider;

import com.edd.rest.api.TypedRequest;
import org.springframework.core.ParameterizedTypeReference;

/**
 * Selects the expected response type.
 */
public interface ResponseTypeDecider {

    /**
     * Set expected response type by providing a response type class.
     *
     * @param type nonnull response type.
     * @param <T>  object response type.
     * @return request with set response type.
     */
    <T> TypedRequest<T> expect(Class<T> type);

    /**
     * Set expected response type by providing a parametrized type reference.
     *
     * @param reference nonnull parameterized type reference whose type is to be used in response.
     * @param <T>       object response type.
     * @return request with set response type reference.
     */
    <T> TypedRequest<T> expect(ParameterizedTypeReference<T> reference);
}