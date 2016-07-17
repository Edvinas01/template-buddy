package com.edd.rest;

import com.edd.rest.api.configuration.ConfigurationConfigurer;
import com.edd.rest.api.configuration.GlobalConfigurationConfigurer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;

public class TemplateBuddy extends RestTemplate {

    /**
     * Create a plain template buddy instance.
     */
    public TemplateBuddy() {
    }

    /**
     * Create template buddy instance with a request factory.
     *
     * @param requestFactory request factory.
     */
    public TemplateBuddy(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    /**
     * Create request builder by specifying base url and url variables. Example:
     *
     * @param url          nonnull base url.
     * @param uriVariables url variables.
     * @return request builder.
     */
    public BuddyRequest fromUrl(String url, Object... uriVariables) {
        return new BuddyRequest(this, url, uriVariables);
    }

    /**
     * Create configuration configurer instance.
     *
     * @return configuration configurer instance.
     */
    public static GlobalConfigurationConfigurer configure() {
        return new BuddyConfigurationHolder(new AntPathMatcher());
    }

    /**
     * Create a configuration instance.
     *
     * @return configuration instance.
     */
    public static ConfigurationConfigurer configuration() {
        return new BuddyConfiguration();
    }
}