package com.razanpardazesh.mtglibrary.CustomView.recycler;

/**
 * Created by Home on 10/15/2016.
 */

public class MTGRecyclerException extends Exception {
    public MTGRecyclerException() {
    }

    public MTGRecyclerException(String message) {
        super(message);
    }

    public MTGRecyclerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MTGRecyclerException(Throwable cause) {
        super(cause);
    }
}
