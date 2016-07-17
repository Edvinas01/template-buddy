package com.edd.rest;

import com.edd.rest.api.configuration.Configuration;
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

    @Override
    protected void prepareConnection(HttpURLConnection connection,
                                     String httpMethod) throws IOException {

        super.prepareConnection(connection, httpMethod);

        Configuration configuration = configurations.getUrlConfiguration(connection.getURL());

        // If url specific configuration was not found, try to use global.
        if (configuration == null) {
            configuration = configurations.getGlobalConfiguration();
        }

        // If global or url specific configuration was found.
        if (configuration != null) {

            // Set connect and read timeouts only if specified.
            if (configuration.getConnectTimeout() != null) {
                connection.setConnectTimeout(configuration.getConnectTimeout());
            }

            if (configuration.getReadTimeout() != null) {
                connection.setReadTimeout(configuration.getReadTimeout());
            }

            // Add header values to the request. todo test how this works
            for (Map.Entry<String, List<String>> entry :
                    configuration.getHttpHeaders().entrySet()) {

                for (String value : entry.getValue()) {
                    connection.addRequestProperty(entry.getKey(), value);
                }
            }
        }
    }
}