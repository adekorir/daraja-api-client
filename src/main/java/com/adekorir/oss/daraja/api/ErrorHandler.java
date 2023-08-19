package com.adekorir.oss.daraja.api;

/**
 * This provides exception handling functionalities to the client and subclasses.
 * <p>
 * This interface was decided upon instead of throwing an exception in the methods.
 */
public interface ErrorHandler {
    void handleException(String message, Throwable exception);

    void handleError(String errorMessage);
}
