package com.edd.rest;

import com.edd.rest.api.configuration.RequestConfiguration;
import com.edd.rest.api.configuration.ConfigurationHolder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Request factory which handles the setting of various configurations.
 */
public class BuddyRequestFactory extends SimpleClientHttpRequestFactory {

    private final ConfigurationHolder configurations;

    /**
     * Create request factory by providing a configuration holder.
     *
     * @param configurations configuration holder.
     */
    BuddyRequestFactory(ConfigurationHolder configurations) {
        this.configurations = configurations;
    }

    /**
     * Get request factory configuration holder.
     *
     * @return configuration holder.
     */
    public ConfigurationHolder getConfigurations() {
        return configurations;
    }

    @Override
    protected void prepareConnection(HttpURLConnection connection,
                                     String httpMethod) throws IOException {

        super.prepareConnection(connection, httpMethod);

        // Configure global settings first.
        configureConnection(configurations.getGlobalRequestConfiguration(), connection);

        // Configure per request settings.
        configurations.getUrlConfiguration(connection.getURL())
                .ifPresent(c -> configureConnection(c, connection));
    }

    private void configureConnection(RequestConfiguration configuration,
                                     HttpURLConnection connection) {

        // Only override connect timeout if its not null.
        if (configuration.getConnectTimeout() != null) {
            connection.setConnectTimeout(configuration.getConnectTimeout());
        }

        // Only override read timeout if its not null.
        if (configuration.getReadTimeout() != null) {
            connection.setReadTimeout(configuration.getReadTimeout());
        }

        // Add header values to the request.
        for (Map.Entry<String, List<String>> entry :
                configuration.getHttpHeaders().entrySet()) {

            for (String value : entry.getValue()) {
                connection.addRequestProperty(entry.getKey(), value);
            }
        }
    }
}