package com.edd.rest;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class BuddyRequestFactoryTest {

    private BuddyRequestFactory factory;

    @Test
    public void globalConfiguration() throws Exception {

        String header = "test";
        String value1 = "val1";
        String value2 = "val2";

        int connectTimeout = 50;
        int readTimeout = 50;

        factory = (BuddyRequestFactory) TemplateBuddy.configure()

                // Testing if global configuration takes effect.
                .configuration(TemplateBuddy
                        .configuration()
                        .connectTimeout(connectTimeout)
                        .readTimeout(readTimeout)
                        .header(header, value1)
                        .header(header, value2)
                        .build())
                .toFactory();

        HttpURLConnection connection = new MockConnection(new URL("http://localhost:8080/abc/endpoint"));
        factory.prepareConnection(connection, "GET");

        assertThat(connection.getRequestProperties().get(header)).contains(value1, value2);
        assertThat(connection.getConnectTimeout()).isEqualTo(connectTimeout);
        assertThat(connection.getReadTimeout()).isEqualTo(readTimeout);
    }

    @Test
    public void urlConfiguration() throws Exception {

        String header = "test";
        String value1 = "val1";
        String value2 = "val2";

        int connectTimeout = 50;
        int readTimeout = 50;

        factory = (BuddyRequestFactory) TemplateBuddy.configure()

                // Mix in global config which mimics url config.
                .configuration(TemplateBuddy
                        .configuration()
                        .connectTimeout(connectTimeout + 1)
                        .readTimeout(readTimeout)
                        .header(header, value1.toUpperCase())
                        .header(header, value2.toUpperCase())
                        .build())

                // Url configuration should not be overridden by global config.
                .configurationFor("http://localhost:8080/**/endpoint/**", TemplateBuddy
                        .configuration()

                        // Overrides connect timeout.
                        .connectTimeout(connectTimeout)
                        .header(header, value1)
                        .header(header, value2)
                        .build())

                // Configuration that also matches the url.
                .configurationFor("http://localhost:8080/**", TemplateBuddy
                        .configuration()
                        .connectTimeout(1)
                        .readTimeout(1)
                        .build())
                .toFactory();

        HttpURLConnection connection = new MockConnection(

                // Also test out url matching.
                new URL("http://localhost:8080/123/endpoint/123"));

        factory.prepareConnection(connection, "GET");

        assertThat(connection.getRequestProperties().get(header))
                .contains(value1.toUpperCase(), value2.toUpperCase(), value1, value2);

        assertThat(connection.getConnectTimeout()).isEqualTo(connectTimeout);
        assertThat(connection.getReadTimeout()).isEqualTo(readTimeout);
    }

    private static class MockConnection extends HttpURLConnection {

        MockConnection(URL u) {
            super(u);
        }

        @Override
        public void disconnect() {
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {
        }
    }
}