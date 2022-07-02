package com.tuanmhoang.study.tdd.cli;

/**
 * Arguments list
 */
public enum CliArgument {
    TEMPLATE_FILE_ARG("--template-file"),
    PARAMS_FILE_ARG("--params-file"),
    OUTPUT_FILE_ARG("--output-file");

    private final String paramName;

    CliArgument(String paramName){
        this.paramName=paramName;
    }

    public String getParamName() {
        return paramName;
    }
}
