package com.tuanmhoang.study.tdd.mail;

/**
 * Exception for writing to file problem.
 */
public class MailServerFileException extends RuntimeException{
    /**
     * Name of file.
     */
    private final String fileName;

    /**
     * Construcfor for exception.
     * @param message message
     * @param fileName file name
     * @param cause of exception
     */
    public MailServerFileException(final String message, final String fileName, final Throwable cause) {
        super(message, cause);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
