package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.util.HashMap;
import java.util.Map;

public class FileParameterHelper implements ParameterHelper {

    private static final String DEFAULT_TEMPLATE_FILE = "template-study.txt";
    private static final String DEFAULT_PARAMS_FILE = "params.txt";

    private final FileHelper fileHelper;

    private Map<String, String> params;

    public FileParameterHelper(FileHelper fileHelper){
        this.fileHelper = fileHelper;
    }

    @Override
    public Map<String, String> getParams() {
        readParams();
        return params;
    }

    @Override
    public String getTemplateText() {
        //fileHelper = new FileHelper();
        String templateContents = null;
        try {
            templateContents = fileHelper.withTemplateFileName(DEFAULT_TEMPLATE_FILE).readFileContents();
        } catch (FileParameterHelperException e) {
            System.err.println("Exception when read file template: " + e);
        }
        return templateContents;
    }

    @Override
    public void readParams() {
        //fileHelper = new FileHelper();
        params = new HashMap<>();
        String paramsContents = null;
        try {
            paramsContents = fileHelper.withTemplateFileName(DEFAULT_PARAMS_FILE).readFileContents();
            String[] linesData = paramsContents.split("\n");
            for (String line:  linesData) {
                String[] lineData = line.split("=");
                params.put(lineData[0],lineData[1]);
            }
        } catch (FileParameterHelperException e) {
            System.err.println("Exception when read file params template: " + e);
        }
    }
}
