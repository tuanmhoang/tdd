package com.tuanmhoang.study.tdd.helper;

import java.util.Map;

public class CliParameterHelper implements ParameterHelper{
    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public String getTemplateText() {
        return "To: #{address}\n"
            + "Hello #{user},\n"
            + "Today we are studying #{moduleName}."
            + "This message is generated based on the CONSOLE template.";
    }

    @Override
    public void readParams() {

    }
}
