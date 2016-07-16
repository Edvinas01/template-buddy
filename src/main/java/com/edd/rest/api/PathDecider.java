package com.edd.rest.api;

/**
 * Describes request path deciding.
 */
public interface PathDecider {

    /**
     * Decide where to perform the request.
     *
     * @param path         request path.
     * @param urlVariables path url variables.
     * @return simple request.
     */
    SimpleRequest path(String path, Object... urlVariables);
}
