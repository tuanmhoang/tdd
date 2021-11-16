package com.tuanmhoang.study.tdd.helper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CliParameterHelper implements ParameterHelper {

    private Map<String, String> params;

    private final InputStream inputStream;

    public CliParameterHelper(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Map<String, String> getParams() {
        readParams();
        return params;
    }

    @Override
    public String getTemplateText() {
        return "To: #{address}\n"
            + "Hello #{user},\n"
            + "Today we are studying #{moduleName}.\n"
            + "This message is generated based on the CONSOLE template.";
    }

    @Override
    public void readParams() {
        params = new HashMap<>();
        params.put("address", "sample@demo.com");
        params.put("user", "Tuan");
        params.put("moduleName", "TDD");
    }
}
