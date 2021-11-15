package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.util.HashMap;
import java.util.Map;

public class FileParameterHelper implements ParameterHelper {

    private static final String DEFAULT_TEMPLATE_FILE = "template-study.txt";
    private static final String DEFAULT_PARAMS_FILE = "params.txt";

    private FileHelper fileHelper;

    private Map<String, String> params;

    @Override
    public Map<String, String> getParams() {
        readParams();
        return params;
    }

    @Override
    public String getTemplateText() {
        fileHelper = new FileHelper();
        String templateContents = null;
        try {
            templateContents = fileHelper.withTemplateFileName(DEFAULT_TEMPLATE_FILE).readFileContents();
        } catch (FileParameterHelperException e) {
            e.printStackTrace();
        }
        return templateContents;
    }

    @Override
    public void readParams() {
        params = new HashMap<>();
    }
}
