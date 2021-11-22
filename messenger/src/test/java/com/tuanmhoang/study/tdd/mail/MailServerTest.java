package com.tuanmhoang.study.tdd.mail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServerTest {

    private MailServer mailServer;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        System.setOut(new PrintStream(outputStreamCaptor, false, StandardCharsets.UTF_8.toString()));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Print message of email to console")
    public void sendWithMailServerCli_shouldSuccess() throws UnsupportedEncodingException {
        MailServer mailServer = new MailServerCli(System.out);

        String addresses = "some@example.com";
        String messageText = "message with params";

        mailServer.send(addresses, messageText);

        assertEquals(
            String.format(
                "to: %s%n"
                    + "%s", addresses, messageText
            ),
            outputStreamCaptor.toString(String.valueOf(StandardCharsets.UTF_8)).trim()
        );
    }
}
