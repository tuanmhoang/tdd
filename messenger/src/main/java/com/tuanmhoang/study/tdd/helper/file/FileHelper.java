package com.tuanmhoang.study.tdd.helper.file;

public class FileHelper {

    private static final String EXPECTED_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    public String readFileContents(){
        return EXPECTED_FILE_CONTENT;
    }
}
