package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import java.util.Map;

public class FileParameterHelper implements ParameterHelper{

    private FileHelper fileHelper;

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public String getTemplateText() {
        // read file
        // get text
        // return text
        return "this is not null";
    }

    @Override
    public void readParams() {

    }
}
