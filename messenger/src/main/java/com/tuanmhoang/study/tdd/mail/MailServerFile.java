package com.tuanmhoang.study.tdd.mail;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        final String toLine = String.format("to: %s%n", address);
        try {
            final String output = toLine + content;
            Files.write(outputFile, output.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new MailServerFileException("Problem with writing to output file", outputFile.toString(), e);
        }
    }
}
