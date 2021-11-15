package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.cli.CliArgumentException;
import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterHelperTest {

    private ParameterHelper parameterHelper;
    private static final String DEFAULT_PARAMETER_FILE_NAME = "params.txt";

    private static final String EXPECTED_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    @Test
    public void getTemplateText_withCliParameterHelper(){
        parameterHelper = new CliParameterHelper();
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertTrue(templateText.contains("This message is generated based on the CONSOLE template."));
    }

    @Test
    public void getTemplateText_withFileParameterHelper(){
        parameterHelper = new FileParameterHelper();
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertEquals(EXPECTED_FILE_CONTENT, templateText);
    }

    @Test
    public void getParams_shouldSuccess(){
        parameterHelper = new FileParameterHelper();
        assertNotNull(parameterHelper.getParams());
        assertEquals(2, parameterHelper.getParams().size());
        assertEquals("Tuan",parameterHelper.getParams().get("user"));
        assertEquals("TDD",parameterHelper.getParams().get("moduleName"));
    }

    @Test
    public void getParams_shouldBeZeroWhenReadParamsTemplateFileThrowException(){
        parameterHelper = new FileParameterHelper();
        assertNotNull(parameterHelper.getParams());
        assertEquals(0,parameterHelper.getParams().size());
    }

}
