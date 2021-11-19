package com.tuanmhoang.study.tdd.helper.exception;

/**
 * Exception is thrown when format of parameter is wrong.
 */
public class ParameterWrongFormatException extends RuntimeException {
    /**
     * Constructor
     *
     * @param message of exception.
     */
    public ParameterWrongFormatException(final String message) {
        super(message);
    }
}
