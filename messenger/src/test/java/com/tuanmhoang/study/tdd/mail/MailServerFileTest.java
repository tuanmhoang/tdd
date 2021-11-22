package com.tuanmhoang.study.tdd.mail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailServerFileTest {

    @Test
    @DisplayName("Write message to file")
    public void writeMessageToFile(@TempDir Path tempDir) throws IOException {
        Path outputFile = tempDir.resolve("messageFile");
        String addresses = "some@example.com";
        String message = "some message";

        MailServer mailServer = new MailServerFile(outputFile);
        mailServer.send(addresses, message);

        List<String> expectedMessage = Arrays.asList("to: some@example.com",
            "some message");

        assertTrue(Files.exists(outputFile), "Output file should exist");
        assertLinesMatch(expectedMessage, Files.readAllLines(outputFile));
    }
}
