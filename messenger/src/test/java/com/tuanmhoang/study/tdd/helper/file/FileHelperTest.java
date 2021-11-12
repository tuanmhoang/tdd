package com.tuanmhoang.study.tdd.helper.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileHelperTest {
    private FileHelper fileHelper = new FileHelper();

    private static final String EXPECTED_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    @Test
    public void readFileContent_shouldSuccess(){
        String fileContents = fileHelper.readFileContents();
        assertNotNull(fileContents);
        assertEquals(EXPECTED_FILE_CONTENT, fileContents);
    }
}
