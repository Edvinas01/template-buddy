package com.edd.rest.api.configuration;

import java.net.URL;

/**
 * Holds global and per url request configuration settings.
 */
public interface ConfigurationHolder {

    /**
     * Get global configuration settings.
     *
     * @return global configuration settings or null if not set.
     */
    Configuration getGlobalConfiguration();

    /**
     * Get configuration settings for this specific url.
     *
     * @param url nonnull url whose configuration settings to get.
     * @return url configuration settings or null if they were not set.
     */
    Configuration getUrlConfiguration(URL url);
}