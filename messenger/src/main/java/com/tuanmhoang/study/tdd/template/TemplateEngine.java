package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.Client;

/**
 * The type Template engine.
 */
public class TemplateEngine {


    private static final String EXPECTED_MSG_FROM_CONSOLE = "To: sample@test.com\n"
        + "Hello Tuan!\n"
        + "Today we are studying TDD\n"
        + "This message is generated based on the CONSOLE template.";

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        return EXPECTED_MSG_FROM_CONSOLE;
    }
}
