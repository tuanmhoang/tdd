package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParameterHelperTest {

    private ParameterHelper parameterHelper;
    private FileHelper fileHelper;
    private static final String DEFAULT_PARAMETER_FILE_NAME = "params.txt";

    private static final String EXPECTED_TEMPLATE_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    private static final String EXPECTED_PARAMS_FILE_CONTENT ="user=Tuan\n"
        + "moduleName=TDD";

    @Test
    public void getTemplateText_withCliParameterHelper(){
        parameterHelper = new CliParameterHelper();
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
        assertTrue(templateText.contains("This message is generated based on the CONSOLE template."));
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
//        fileHelper = spy(FileHelper.class);
//        when(fileHelper.readFileContents()).thenReturn(EXPECTED_PARAMS_FILE_CONTENT);
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
