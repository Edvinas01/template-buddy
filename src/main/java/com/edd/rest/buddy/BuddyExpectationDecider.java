package com.edd.rest.buddy;

import com.edd.rest.api.ExpectationDecider;
import com.edd.rest.api.TypedRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;

public class BuddyExpectationDecider implements ExpectationDecider {

    private final RestOperations restOperations;
    private final Object payload;

    public BuddyExpectationDecider(RestOperations restOperations,
                                   Object payload) {

        this.restOperations = restOperations;
        this.payload = payload;
    }

    @Override
    public <T> TypedRequest<T> expect(Class<T> responseType) {
        Assert.notNull(responseType);

        return new BuddyRequestExecutor<>(
                restOperations,
                null,
                responseType,
                null,
                payload
        );
    }

    @Override
    public <T> TypedRequest<T> expect(ParameterizedTypeReference<T> reference) {
        Assert.notNull(reference);

        return new BuddyRequestExecutor<>(
                restOperations,
                reference,
                null,
                null,
                payload
        );
    }
}
