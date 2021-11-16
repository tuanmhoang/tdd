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

    private static final String EXPECTED_TEMPLATE_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    private static final String EXPECTED_CONSOLE_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the CONSOLE template.";

    @Test
    public void getTemplateText_withCliParameterHelper(){
        parameterHelper = new CliParameterHelper(System.in);
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertTrue(templateText.contains("This message is generated based on the CONSOLE template."));
    }

    @Test
    public void getParamsCli_shouldSuccess(){
        Map<String, String> mapParams1 = new HashMap<>();
        mapParams1.put("address", "sample@demo.com");
        mapParams1.put("user", "Tuan");
        mapParams1.put("moduleName", "TDD");

        String input1 = EXPECTED_CONSOLE_FILE_CONTENT.replace(PLACE_HOLDER_ADDRESS, mapParams1.get("address"))
            .replace(PLACE_HOLDER_USER, mapParams1.get("user"))
            .replace(PLACE_HOLDER_MODULE, mapParams1.get("moduleName"));

        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(input1.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStreamCaptor);

        parameterHelper = new CliParameterHelper(System.in);

        Map<String, String> params1 = parameterHelper.getParams();

        assertNotNull(params1);
        assertEquals(3,params1.size());
        assertEquals("sample@demo.com",params1.get("address"));
        assertEquals("Tuan",params1.get("user"));
        assertEquals("TDD",params1.get("moduleName"));
        //******//
//        Map<String, String> mapParams2 = new HashMap<>();
//        mapParams2.put("address", "sample2@demo.com");
//        mapParams2.put("user", "Hoang");
//        mapParams2.put("moduleName", "AWS");
//
//        parameterHelper = new CliParameterHelper(System.in);
//
//        Map<String, String> params1 = parameterHelper.getParams();
//
//        assertNotNull(params1);
//        assertEquals(3,params1.size());
//        assertEquals("sample@demo.com",params1.get("address"));
//        assertEquals("Tuan",params1.get("user"));
//        assertEquals("TDD",params1.get("moduleName"));
    }

    @Test
    public void getTemplateText_withFileParameterHelper(){
        fileHelper = new FileHelper();
        parameterHelper = new FileParameterHelper(fileHelper);
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertEquals(EXPECTED_TEMPLATE_FILE_CONTENT, templateText);
    }

    @Test
    public void getParams_shouldSuccess(){
        fileHelper = new FileHelper();
        parameterHelper = new FileParameterHelper(fileHelper);
        assertNotNull(parameterHelper.getParams());
        assertEquals(2, parameterHelper.getParams().size());
        assertEquals("Tuan",parameterHelper.getParams().get("user"));
        assertEquals("TDD",parameterHelper.getParams().get("moduleName"));
    }

    @Test
    public void getParams_shouldBeZeroWhenReadParamsTemplateFileThrowException(){
        fileHelper = spy(FileHelper.class);
        doThrow(new FileParameterHelperException("expected","notExist")).when(fileHelper).readFileContents();

        parameterHelper = new FileParameterHelper(fileHelper);
        assertNotNull(parameterHelper.getParams());
        assertEquals(0,parameterHelper.getParams().size());
    }



}
