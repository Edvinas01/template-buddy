package com.edd.rest.api.configuration;

/**
 * Configures a single configuration instance.
 */
public interface ConfigurationBuilder {

    /**
     * Set read timeout for this configuration.
     *
     * @param readTimeout read timeout.
     * @return updated configuration properties object.
     */
    ConfigurationBuilder readTimeout(int readTimeout);

    /**
     * Set connect timeout for this configuration.
     *
     * @param connectTimeout connect timeout.
     * @return updated configuration properties object.
     */
    ConfigurationBuilder connectTimeout(int connectTimeout);

    /**
     * Add http header to header list for this configuration.
     *
     * @param name  nonnull header name.
     * @param value nonnull header value.
     * @return updated configuration properties object..
     */
    ConfigurationBuilder header(String name, String value);

    /**
     * Finalize configuration properties.
     *
     * @return built configuration properties.
     */
    RequestConfiguration build();
}