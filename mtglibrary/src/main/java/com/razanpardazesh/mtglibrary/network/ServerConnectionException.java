package com.razanpardazesh.mtglibrary.network;

/**
 * Created by Home on 10/15/2016.
 */

public class ServerConnectionException extends Exception {
    public ServerConnectionException() {
    }

    public ServerConnectionException(String message) {
        super(message);
    }

    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerConnectionException(Throwable cause) {
        super(cause);
    }
}
