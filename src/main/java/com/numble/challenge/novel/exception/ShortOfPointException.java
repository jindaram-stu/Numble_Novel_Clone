package com.numble.challenge.novel.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class ShortOfPointException extends RuntimeException {

    public ShortOfPointException() {
        super();
    }

    public ShortOfPointException(String message) {
        super(message);
    }

    public ShortOfPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortOfPointException(Throwable cause) {
        super(cause);
    }

    protected ShortOfPointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
