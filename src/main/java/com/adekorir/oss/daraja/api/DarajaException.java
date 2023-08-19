package com.adekorir.oss.daraja.api;

/**
 * This exception class is a simple wrapper for exceptions thrown by connections.
 * <p>
 * This will be available for most exceptions that will likely be thrown by the REST api.
 * The only requirement is for the user to provide a message at the very least.
 * <p>
 * NOTE: It is NOT extensible.
 */
public final class DarajaException extends Exception {

    private static final long serialVersionUID = 1193740464191995855L;

    /**
     * A constructor that provides a message and a reference to the cause.
     * <p>
     * This provides wrapper functionality for any exception thrown by the APIs.
     *
     * @param message The message describing the exception.
     * @param cause   The root cause of the exception.
     */
    public DarajaException(String message, Throwable cause) {
        super(message, cause);
    }
}