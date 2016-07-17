package com.edd.rest.api.configuration;

public interface ConfigurationConfigurer {

    /**
     * Set read timeout.
     *
     * @param readTimeout read timeout.
     * @return updated configuration properties object.
     */
    ConfigurationConfigurer readTimeout(int readTimeout);

    /**
     * Set connect timeout.
     *
     * @param connectTimeout connect timeout.
     * @return updated configuration properties object.
     */
    ConfigurationConfigurer connectTimeout(int connectTimeout);

    /**
     * Add http header to header list for this configuration.
     *
     * @param name  nonnull header name.
     * @param value nonnull header value.
     * @return updated configuration properties object..
     */
    ConfigurationConfigurer header(String name, String value);

    /**
     * Finalize configuration properties.
     *
     * @return built configuration properties.
     */
    Configuration build();
}