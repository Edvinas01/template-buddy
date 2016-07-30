package com.edd.rest.api.configuration;

import org.springframework.http.HttpHeaders;

/**
 * Request configuration.
 */
public interface RequestConfiguration {

    /**
     * Get http headers.
     *
     * @return nonnull http headers.
     */
    HttpHeaders getHttpHeaders();

    /**
     * Get read timeout.
     *
     * @return read timeout or null if it was not set.
     */
    Integer getReadTimeout();

    /**
     * Get connect timeout.
     *
     * @return connect timeout or null if it was not set.
     */
    Integer getConnectTimeout();
}