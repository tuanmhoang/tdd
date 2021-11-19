package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.Client;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import com.tuanmhoang.study.tdd.helper.ParameterHelper;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TemplateEngineTest {
    private TemplateEngine templateEngine = new TemplateEngine();

    private Template template;

    private ParameterHelper parameterHelper;

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
        // case 1
        parameterHelper = mock(CliParameterHelper.class);
        doReturn(mockParams("sample@test.com", "Tuan", "TDD"))
            .when(parameterHelper).getParams();

        template = new Template(parameterHelper);

        String msg = templateEngine.generateMessage(template, client);
        assertEquals(msg, EXPECTED_MSG_FROM_CONSOLE);

        // case 2
        doReturn(mockParams("sample@test.com", "Tuan2", "AWS"))
            .when(parameterHelper).getParams();

        template = new Template(parameterHelper);

        msg = templateEngine.generateMessage(template, client);

        String expectedMsg2 = "To: sample@test.com\n"
            + "Hello Tuan2!\n"
            + "Today we are studying AWS\n"
            + "This message is generated based on the CONSOLE template.";
        assertEquals(msg, expectedMsg2);
    }

    private Map<String, String> mockParams(String addr, String user, String module) {
        Map<String, String> mocked = new HashMap<>();
        mocked.put("address", addr);
        mocked.put("user", user);
        mocked.put("moduleName", module);
        return mocked;
    }

}
