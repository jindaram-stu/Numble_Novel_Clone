package com.numble.challenge.novel.exception;

public class NovelNotFoundException extends RuntimeException {
    public NovelNotFoundException() {
        super();
    }

    public NovelNotFoundException(String message) {
        super(message);
    }

    public NovelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NovelNotFoundException(Throwable cause) {
        super(cause);
    }

    protected NovelNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
