package com.tuanmhoang.study.tdd.mail;

import java.nio.file.Path;

public class MailServerFile implements MailServer{

    /**
     * File for output message
     */
    private final Path outputFile;

    /**
     *
     * @param outputFile file for output message.
     */
    public MailServerFile(final Path outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void send(String address, String content) {

    }
}
