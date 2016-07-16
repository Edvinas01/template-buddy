package com.edd.rest.buddy;

import com.edd.rest.api.ConcreteRequestExecutor;
import com.edd.rest.api.RequestExecutor;
import com.edd.rest.api.TypedRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestOperations;

public class BuddyRequestExecutor<T> implements RequestExecutor<T>,
        ConcreteRequestExecutor<T>,
        TypedRequest<T> {

    private final RestOperations restOperations;

    private final ParameterizedTypeReference<T> reference;
    private final Class<T> responseType;
    private final HttpMethod method;
    private final Object payload;

    public BuddyRequestExecutor(RestOperations restOperations,
                                ParameterizedTypeReference<T> reference,
                                Class<T> responseType,
                                HttpMethod method,
                                Object payload) {

        this.restOperations = restOperations;
        this.reference = reference;
        this.responseType = responseType;
        this.method = method;
        this.payload = payload;
    }

    @Override
    public ResponseEntity<T> executeForEntity() {
        return exchange(method);
    }

    @Override
    public ResponseEntity<T> postForEntity() {
        return exchange(HttpMethod.POST);
    }

    @Override
    public ResponseEntity<T> getForEntity() {
        return exchange(HttpMethod.GET);
    }

    @Override
    public ResponseEntity<T> deleteForEntity() {
        return exchange(HttpMethod.DELETE);
    }

    @Override
    public RequestExecutor<T> method(HttpMethod method) {
        Assert.notNull(method);

        return new BuddyRequestExecutor<>(
                restOperations,
                reference,
                responseType,
                method,
                payload
        );
    }

    /**
     * Perform a request by providing request method type.
     *
     * @param method request method.
     * @return request response.
     */
    private ResponseEntity<T> exchange(HttpMethod method) {
        HttpEntity<?> entity = new HttpEntity<>(payload, new LinkedMultiValueMap<>());

        if (responseType != null) {
            return restOperations.exchange(null, method, entity, responseType);
        } else {
            return restOperations.exchange(null, method, entity, reference);
        }
    }
}
