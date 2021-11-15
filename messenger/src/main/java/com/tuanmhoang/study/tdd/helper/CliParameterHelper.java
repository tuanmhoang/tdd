package com.tuanmhoang.study.tdd.helper;

import java.util.HashMap;
import java.util.Map;

public class CliParameterHelper implements ParameterHelper{

    private Map<String, String> params;

    @Override
    public Map<String, String> getParams() {
        readParams();
        return params;
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
        params = new HashMap<>();
    }
}
