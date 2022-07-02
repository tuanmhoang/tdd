package com.tuanmhoang.study.tdd.cli;

/**
 * Exception is thrown when argument number of
 * arguments are wrong or name of argument are wrong.
 */
public class CliArgumentException extends RuntimeException {
    /**
     * Number of provided arguments.
     */
    private final int numberOfParameters;

    /**
     * Constructor of exception
     * @param message message of exception
     * @param numberOfParameters number of arguments
     */
    public CliArgumentException(final String message, final int numberOfParameters) {
        super(message);
        this.numberOfParameters = numberOfParameters;
    }

    /**
     * Return number of argument.
     * @return number of arguments
     */
    public int getNumberOfParameters() {
        return numberOfParameters;
    }
}
