package com.edd.rest.api.configuration;

/**
 * Initial configuration interface, which allows the setting of global request configuration.
 */
public interface GlobalConfigurationConfigurer extends UrlConfigurationConfigurer {

    /**
     * Set global request configuration.
     *
     * @param configuration nonnull global configuration.
     * @return updated configurer.
     */
    UrlConfigurationConfigurer globalConfiguration(Configuration configuration);
}