package com.edd.rest.api.configuration;

import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * Intermediate interface for configuring url matched configurations.
 */
public interface UrlConfigurationConfigurer {

    /**
     * Add url configuration.
     *
     * @param matcher       nonnull ant path matcher for the url.
     * @param configuration configuration for the url matcher.
     * @return updated configurer.
     */
    UrlConfigurationConfigurer urlConfiguration(String matcher,
                                                Configuration configuration);

    /**
     * Finalize configuration and create a http factory.
     *
     * @return http factory with injected configuration settings.
     */
    ClientHttpRequestFactory toFactory();
}