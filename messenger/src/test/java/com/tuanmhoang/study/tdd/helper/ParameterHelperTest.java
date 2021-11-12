package com.tuanmhoang.study.tdd.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParameterHelperTest {

    private ParameterHelper parameterHelper;

    @Test
    public void getTemplateText_withCliParameterHelper(){
        parameterHelper = new CliParameterHelper();
        String templateText = parameterHelper.getTemplateText();
        assertNotNull(templateText);
    }

}
