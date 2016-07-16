package com.edd.rest.buddy;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class TemplateBuddy extends RestTemplate {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * Create request builder by specifying base url and url variables. Example:
     *
     * @param url          nonnull base url.
     * @param uriVariables url variables.
     * @return request builder.
     */
    public DefaultRequestBuilder fromUrl(String url, Object... uriVariables) {
        return new DefaultRequestBuilder(this, url, uriVariables);
    }

    // todo replace by configuration
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }
}