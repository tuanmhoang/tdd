package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

public class ParameterHelperTest {

    private ParameterHelper parameterHelper;

    private FileHelper fileHelper;

    private static final String DEFAULT_PARAMETER_FILE_NAME = "params.txt";

    private static final String PLACE_HOLDER_ADDRESS = "#{address}";

    private static final String PLACE_HOLDER_USER = "#{user}";

    private static final String PLACE_HOLDER_MODULE = "#{moduleName}";

    private static final String EXPECTED_TEMPLATE_FILE_CONTENT = "To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    private static final String EXPECTED_CONSOLE_FILE_CONTENT = "To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the CONSOLE template.";

    @Test
    public void getTemplateText_withCliParameterHelper() {
        parameterHelper = new CliParameterHelper(System.in);
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertTrue(templateText.contains("This message is generated based on the CONSOLE template."));
    }

    @Test
    public void getParamsCli_shouldSuccess() {
        // case 1
        Map<String, String> mapParams1 = createMapOfParams(
            "sample@demo.com",
            "Tuan",
            "TDD"
        );

        ByteArrayInputStream inputStreamCaptor1 = buildInputStreamFromMapParams(mapParams1);
        System.setIn(inputStreamCaptor1);

        parameterHelper = new CliParameterHelper(System.in);

        Map<String, String> params1 = parameterHelper.getParams();

        checkMapParams(params1, "sample@demo.com","Tuan","TDD");
        // case 2
        Map<String, String> mapParams2 = createMapOfParams(
            "sample2@demo.com",
            "Hoang",
            "AWS"
        );
        ByteArrayInputStream inputStreamCaptor2 = buildInputStreamFromMapParams(mapParams2);
        System.setIn(inputStreamCaptor2);

        parameterHelper = new CliParameterHelper(System.in);
        Map<String, String> params2 = parameterHelper.getParams();
        checkMapParams(params2, "sample2@demo.com","Hoang","AWS");
    }

    private void checkMapParams(Map<String, String> params, String expectedAddr, String expectedUser, String expectedModule) {
        assertNotNull(params);
        assertEquals(3, params.size());
        assertEquals(expectedAddr, params.get("address"));
        assertEquals(expectedUser, params.get("user"));
        assertEquals(expectedModule, params.get("moduleName"));
    }

    private ByteArrayInputStream buildInputStreamFromMapParams(Map<String, String> mapParams) {
        String inputParams1 = mapParams.keySet().stream()
            .map(key -> key + "=" + mapParams.get(key))
            .collect(Collectors.joining(System.lineSeparator(), "", System.lineSeparator()));

        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(inputParams1.getBytes(StandardCharsets.UTF_8));
        return inputStreamCaptor;
    }

    private Map<String, String> createMapOfParams(String addr, String user, String module) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("address", addr);
        mapParams.put("user", user);
        mapParams.put("moduleName", module);
        return mapParams;
    }

    @Test
    public void getTemplateText_withFileParameterHelper() {
        fileHelper = new FileHelper();
        parameterHelper = new FileParameterHelper(fileHelper);
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertEquals(EXPECTED_TEMPLATE_FILE_CONTENT, templateText);
    }

    @Test
    public void getParams_shouldSuccess() {
        fileHelper = new FileHelper();
        parameterHelper = new FileParameterHelper(fileHelper);
        assertNotNull(parameterHelper.getParams());
        assertEquals(2, parameterHelper.getParams().size());
        assertEquals("Tuan", parameterHelper.getParams().get("user"));
        assertEquals("TDD", parameterHelper.getParams().get("moduleName"));
    }

    @Test
    public void getParams_shouldBeZeroWhenReadParamsTemplateFileThrowException() {
        fileHelper = spy(FileHelper.class);
        doThrow(new FileParameterHelperException("expected", "notExist")).when(fileHelper).readFileContents();

        parameterHelper = new FileParameterHelper(fileHelper);
        assertNotNull(parameterHelper.getParams());
        assertEquals(0, parameterHelper.getParams().size());
    }

}
