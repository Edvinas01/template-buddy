package com.edd.rest.api.configuration;

/**
 * Initial configuration interface, which allows the setting of global request configuration.
 */
public interface ConfigurationConfigurer extends UrlConfigurationConfigurer {

    /**
     * Set global request configuration settings, which will apply on all requests. Note that these settings can be
     * overridden by url configuration settings.
     *
     * @param configuration nonnull global configuration settings.
     * @return updated url configuration configurer.
     */
    UrlConfigurationConfigurer configuration(RequestConfiguration configuration);
}