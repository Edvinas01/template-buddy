package com.edd.rest;

import com.edd.rest.api.configuration.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Default configuration holder.
 */
public class BuddyConfigurationHolder implements ConfigurationHolder,
        GlobalConfigurationConfigurer,
        UrlConfigurationConfigurer {

    private final Map<String, Configuration> urlConfigurations = new HashMap<>();
    private final PathMatcher pathMatcher;

    private Configuration globalConfiguration;

    BuddyConfigurationHolder(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    @Override
    public Configuration getGlobalConfiguration() {
        return globalConfiguration;
    }

    @Override
    public Configuration getUrlConfiguration(URL url) {
        Assert.notNull(url);

        for (Map.Entry<String, Configuration> entry : urlConfigurations.entrySet()) {
            if (pathMatcher.match(entry.getKey(), url.toString())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public UrlConfigurationConfigurer globalConfiguration(Configuration globalConfiguration) {
        Assert.notNull(globalConfiguration);

        this.globalConfiguration = globalConfiguration;
        return this;
    }

    @Override
    public UrlConfigurationConfigurer urlConfiguration(String matcher,
                                                       Configuration urlConfiguration) {

        Assert.notNull(matcher);
        Assert.notNull(urlConfiguration);

        this.urlConfigurations.put(matcher, urlConfiguration);
        return this;
    }

    @Override
    public SimpleClientHttpRequestFactory toFactory() {
        return new BuddyRequestFactory(this);
    }
}