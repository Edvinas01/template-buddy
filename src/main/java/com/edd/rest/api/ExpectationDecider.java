package com.edd.rest.api;

import org.springframework.core.ParameterizedTypeReference;

/**
 * Describes response type decision making.
 */
public interface ExpectationDecider {

    /**
     * Set expected response type by providing a response type class.
     *
     * @param responseType type to be used in response.
     * @param <T>          response type.
     * @return request with set response type.
     */
    <T> TypedRequest<T> expect(Class<T> responseType);

    /**
     * Set expected response type by providing a parametrized type reference.
     *
     * @param reference parameterized type reference whose type is to be used in response.
     * @param <T>       response type.
     * @return request with set response type.
     */
    <T> TypedRequest<T> expect(ParameterizedTypeReference<T> reference);
}
