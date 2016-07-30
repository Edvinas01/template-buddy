package com.edd.rest;

import com.edd.rest.api.PayloadRequest;
import com.edd.rest.api.Request;
import com.edd.rest.api.TypedRequest;
import com.edd.rest.api.executor.ConcreteRequestExecutor;
import com.edd.rest.api.executor.GenericRequestExecutor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;

/**
 * Default implementation of the request api.
 */
public class BuddyRequest implements Request, PayloadRequest {

    private static final ParameterizedTypeReference<Object> OBJECT_TYPE_REFERENCE =
            new ParameterizedTypeReference<Object>() {
            };

    private final UriComponentsBuilder uriComponentsBuilder;
    private final TemplateBuddy templateBuddy;
    private Object payload;

    // Http headers for this request.
    private final HttpHeaders headers = new HttpHeaders();

    /**
     * Create a request builder instance.
     *
     * @param templateBuddy nonnull template buddy instance.
     * @param baseUrl       base request url.
     * @param uriVariables  base url uri variables.
     */
    BuddyRequest(TemplateBuddy templateBuddy, String baseUrl, Object... uriVariables) {
        Assert.notNull(templateBuddy);
        Assert.notNull(baseUrl);

        // Create components builder so we could add more params later on.
        this.uriComponentsBuilder = UriComponentsBuilder.fromUri(templateBuddy
                .getUriTemplateHandler()
                .expand(baseUrl, uriVariables));

        this.templateBuddy = templateBuddy;
    }

    @Override
    public Request path(String path, Object... uriVariables) {
        Assert.notNull(path);

        this.uriComponentsBuilder.path(templateBuddy
                .getUriTemplateHandler()
                .expand(path, uriVariables)
                .getPath());

        return this;
    }

    @Override
    public Request param(String name, Object... values) {
        Assert.notNull(name);

        this.uriComponentsBuilder.queryParam(name, values);
        return this;
    }

    @Override
    public Request header(String name, String value) {
        Assert.notNull(name);
        Assert.notNull(value);

        headers.add(name, value);
        return this;
    }

    @Override
    public PayloadRequest payload(Object payload) {
        this.payload = payload;
        return this;
    }

    @Override
    public <T> TypedRequest<T> expect(Class<T> type) {
        return expect(new ParameterizedTypeReference<T>() {

            @Override
            public Type getType() {
                return type;
            }
        });
    }

    @Override
    public <T> TypedRequest<T> expect(ParameterizedTypeReference<T> reference) {
        return new Executor<>(reference);
    }

    @Override
    public GenericRequestExecutor method(HttpMethod method) {
        return new Executor<>(OBJECT_TYPE_REFERENCE)
                .method(method);
    }

    @Override
    public ResponseEntity post() {
        return new Executor<>(OBJECT_TYPE_REFERENCE)
                .post();
    }

    @Override
    public ResponseEntity get() {
        return new Executor<>(OBJECT_TYPE_REFERENCE)
                .get();
    }

    @Override
    public ResponseEntity delete() {
        return new Executor<>(OBJECT_TYPE_REFERENCE)
                .delete();
    }

    /**
     * Request which is prepared for execution with a set response type.
     *
     * @param <R> response object type.
     */
    private class Executor<R> implements GenericRequestExecutor<R>,
            ConcreteRequestExecutor<R>,
            TypedRequest<R> {

        private final ParameterizedTypeReference<R> reference;
        private HttpMethod method;

        private Executor(ParameterizedTypeReference<R> reference) {
            this.reference = reference;
        }

        @Override
        public ResponseEntity<R> execute() {

            // Create uri from query params and base url.
            URI uri = uriComponentsBuilder.build().toUri();

            // Perform the request.
            return templateBuddy.exchange(uri,
                    method,
                    payload == null ?
                            new HttpEntity<>(headers) :
                            new HttpEntity<>(payload, headers),
                    reference);
        }

        @Override
        public ResponseEntity<R> post() {
            return method(HttpMethod.POST)
                    .execute();
        }

        @Override
        public ResponseEntity<R> get() {
            return method(HttpMethod.GET)
                    .execute();
        }

        @Override
        public ResponseEntity<R> delete() {
            return method(HttpMethod.DELETE)
                    .execute();
        }

        @Override
        public GenericRequestExecutor<R> method(HttpMethod method) {
            this.method = method;
            return this;
        }
    }
}