package com.tuanmhoang.study.tdd.helper.file;

public class FileParameterHelperException extends RuntimeException{
    /**
     * Name of file.
     */
    private final String fileName;

    /**
     * Constructor of exception
     * @param message message of exception
     * @param fileName name of file
     */
    public FileParameterHelperException(final String message, final String fileName) {
        super(message);
        this.fileName = fileName;
    }

    /**
     * Return number of argument.
     * @return number of arguments
     */
    public String getFileName() {
        return fileName;
    }
}
