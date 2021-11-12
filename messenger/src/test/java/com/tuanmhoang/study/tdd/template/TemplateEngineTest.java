package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateEngineTest {
    private TemplateEngine templateEngine = new TemplateEngine();

    private Template template;

    private Client client = new Client("sample@test.com");

    @Test
    public void generateMessage_withTemplateCli_success() {
        template = new Template();

    }
}
