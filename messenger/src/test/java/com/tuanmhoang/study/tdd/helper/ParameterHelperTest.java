package com.tuanmhoang.study.tdd.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterHelperTest {

    private ParameterHelper parameterHelper;

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
    }

}
