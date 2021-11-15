package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.io.IOException;
import java.util.Map;

public class FileParameterHelper implements ParameterHelper {

    private static final String DEFAULT_TEMPLATE_FILE = "template-study.txt";

    private FileHelper fileHelper;

    @Override
    public Map<String, String> getParams() {
        return null;
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

    }
}
