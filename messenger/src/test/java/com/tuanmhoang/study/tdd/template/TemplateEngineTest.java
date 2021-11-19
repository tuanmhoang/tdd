package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.Client;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateEngineTest {
    private TemplateEngine templateEngine = new TemplateEngine();

    private Template template;

    private Client client = new Client("sample@test.com");

    private static final String EXPECTED_MSG_FROM_FILE = "To: sample@test.com\n"
        + "Hello Tuan!\n"
        + "Today we are studying TDD\n"
        + "This message is generated based on the FILE template.";

    private static final String EXPECTED_MSG_FROM_CONSOLE = "To: sample@test.com\n"
        + "Hello Tuan!\n"
        + "Today we are studying TDD\n"
        + "This message is generated based on the CONSOLE template.";

    @Test
    public void generateMessage_withTemplateCli_success() {
        template = new Template(new CliParameterHelper(System.in));
        String msg = templateEngine.generateMessage(template, client);
        assertEquals(msg, EXPECTED_MSG_FROM_CONSOLE);
    }


}
