package com.edd.rest;

import com.edd.rest.api.configuration.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

import java.net.URL;
import java.util.*;

/**
 * Default configuration holder.
 */
public class BuddyConfigurationHolder implements ConfigurationHolder,
        ConfigurationConfigurer,
        UrlConfigurationConfigurer {

    private final Map<String, RequestConfiguration> urlConfigurations = new LinkedHashMap<>();
    private final PathMatcher pathMatcher;

    private RequestConfiguration globalRequestConfiguration;

    BuddyConfigurationHolder(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public RequestConfiguration getGlobalRequestConfiguration() {
        return globalRequestConfiguration;
    }

    @Override
    public Optional<RequestConfiguration> getUrlConfiguration(URL url) {
        Assert.notNull(url);

        // Collect all configurations that match the provided url.
        for (Map.Entry<String, RequestConfiguration> entry : urlConfigurations.entrySet()) {
            if (pathMatcher.match(entry.getKey(), url.toString())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    @Override
    public UrlConfigurationConfigurer configuration(RequestConfiguration configuration) {
        Assert.notNull(configuration);

        this.globalRequestConfiguration = configuration;
        return this;
    }

    @Override
    public UrlConfigurationConfigurer configurationFor(String matcher,
                                                       RequestConfiguration configuration) {

        Assert.notNull(matcher);
        Assert.notNull(configuration);

        this.urlConfigurations.put(matcher, configuration);
        return this;
    }

    @Override
    public SimpleClientHttpRequestFactory toFactory() {
        return new BuddyRequestFactory(this);
    }
}