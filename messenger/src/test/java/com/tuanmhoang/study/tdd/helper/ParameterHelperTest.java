package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterHelperTest {

    private ParameterHelper parameterHelper;

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

}
