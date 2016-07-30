package com.edd.rest;

import com.edd.rest.api.configuration.RequestConfiguration;
import com.edd.rest.api.configuration.ConfigurationBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;

/**
 * Default implementation of generic request configuration.
 */
public class BuddyConfiguration implements ConfigurationBuilder,
        RequestConfiguration {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    // Timeouts can be null.
    private Integer connectTimeout;
    private Integer readTimeout;

    BuddyConfiguration() {
    }

    @Override
    public ConfigurationBuilder readTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    @Override
    public ConfigurationBuilder connectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    @Override
    public ConfigurationBuilder header(String name, String value) {
        Assert.notNull(name);
        Assert.notNull(value);

        httpHeaders.add(name, value);
        return this;
    }

    @Override
    public RequestConfiguration build() {
        return this;
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    @Override
    public Integer getReadTimeout() {
        return readTimeout;
    }

    @Override
    public Integer getConnectTimeout() {
        return connectTimeout;
    }
}
