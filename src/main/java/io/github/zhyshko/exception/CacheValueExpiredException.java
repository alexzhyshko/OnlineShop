package io.github.zhyshko.exception;

public class CacheValueExpiredException extends RuntimeException{

    public CacheValueExpiredException(String message) {
        super(message);
    }

}
