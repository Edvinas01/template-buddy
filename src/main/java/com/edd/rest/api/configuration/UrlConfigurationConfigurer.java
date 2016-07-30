package com.edd.rest.api.configuration;

import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * Intermediate interface for configuring url matched configurations.
 */
public interface UrlConfigurationConfigurer {

    /**
     * Create a new configuration instance for a specific url pattern.
     *
     * @param matcher       nonnull ant path matcher for the url.
     * @param configuration configuration for the url matcher.
     * @return updated url configurer.
     */
    UrlConfigurationConfigurer configurationFor(String matcher,
                                                RequestConfiguration configuration);

    /**
     * Finalize configuration and create a http factory.
     *
     * @return http factory with injected configuration settings.
     */
    ClientHttpRequestFactory toFactory();
}