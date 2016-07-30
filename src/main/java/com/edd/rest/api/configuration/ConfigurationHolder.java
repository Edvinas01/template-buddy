package com.edd.rest.api.configuration;

import java.net.URL;
import java.util.Optional;

/**
 * Holds global and per url request configuration settings.
 */
public interface ConfigurationHolder {

    /**
     * Get global configuration settings.
     *
     * @return global configuration settings, never null.
     */
    RequestConfiguration getGlobalRequestConfiguration();

    /**
     * Get configuration settings which match this specific url.
     *
     * @param url nonnull url whose configuration settings to get.
     * @return url configuration settings optional.
     */
    Optional<RequestConfiguration> getUrlConfiguration(URL url);
}