package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateEngineTest {
    private TemplateEngine templateEngine;

    private Template template;

    private Client client = new Client("sample@test.com");

    @Test
    public void generateMessage_withTemplateCli_success() {
        template = new TemplateCli();
        assertEquals("Hello #{user}!/n"
                + "Today we are studying #{moduleName}/n"
                + "This message is generated in CONSOLE mode.",
            template.getMessage());
    }
}
