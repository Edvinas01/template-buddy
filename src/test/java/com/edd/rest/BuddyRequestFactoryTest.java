package com.edd.rest;

import org.junit.Test;
import sun.net.www.protocol.http.HttpURLConnection;

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
                .globalConfiguration(TemplateBuddy
                        .configuration()
                        .connectTimeout(connectTimeout)
                        .readTimeout(readTimeout)
                        .header(header, value1)
                        .header(header, value2)
                        .build())
                .toFactory();

        HttpURLConnection connection = new HttpURLConnection(new URL("http://localhost:8080/abc/endpoint"), null);
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
                .globalConfiguration(TemplateBuddy
                        .configuration()
                        .connectTimeout(connectTimeout + 1)
                        .connectTimeout(readTimeout + 1)
                        .header(header, value1 + "1")
                        .header(header, value2 + "1")
                        .build())

                // Url configuration should not be overridden by global config.
                .urlConfiguration("http://localhost:8080/**/endpoint/**", TemplateBuddy
                        .configuration()
                        .connectTimeout(connectTimeout)
                        .readTimeout(readTimeout)
                        .header(header, value1)
                        .header(header, value2)
                        .build())
                .toFactory();

        HttpURLConnection connection = new HttpURLConnection(

                // Also test out url matching.
                new URL("http://localhost:8080/123/endpoint/123"), null);

        factory.prepareConnection(connection, "GET");

        assertThat(connection.getRequestProperties().get(header)).contains(value1, value2);
        assertThat(connection.getConnectTimeout()).isEqualTo(connectTimeout);
        assertThat(connection.getReadTimeout()).isEqualTo(readTimeout);
    }
}