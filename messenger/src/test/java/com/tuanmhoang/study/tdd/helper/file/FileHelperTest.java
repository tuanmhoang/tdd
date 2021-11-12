package com.tuanmhoang.study.tdd.helper.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileHelperTest {
    private FileHelper fileHelper = new FileHelper();

    @Test
    public void readFileContent_shouldSuccess(){
        String fileContents = fileHelper.readFileContents();
        assertNotNull(fileContents);
    }
}
