package com.tuanmhoang.study.tdd.helper.file;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

public class FileHelperTest {
    private FileHelper fileHelper = new FileHelper();

    private static final String EXPECTED_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    private static final String TEMPLATE_FILE_NAME = "template-study.txt";

    @Test
    public void readFileContent_shouldSuccess() throws IOException, FileParameterHelperException {
        String fileContents = fileHelper.withTemplateFileName(TEMPLATE_FILE_NAME).readFileContents();
        assertNotNull(fileContents);
        assertEquals(EXPECTED_FILE_CONTENT, fileContents);
    }

    @Test
    public void readFileContent_shouldThrowFileParameterHelperException() throws FileParameterHelperException, IOException {
        String notExistFileName = "notExist";
        FileParameterHelperException thrown = assertThrows(
            FileParameterHelperException.class,
            () -> fileHelper.withTemplateFileName(notExistFileName).readFileContents()
        );
        assertEquals(thrown.getMessage(),"File does not exist.");
        assertEquals(thrown.getFileName(), notExistFileName);
    }

    @Test
    public void readFileContent_shouldThrowFileParameterHelperException_whenReadFile() throws FileParameterHelperException, IOException {
        FileHelper mocked = spy(FileHelper.class);
        doThrow(new IOException()).when(mocked).readFileAsString(any(File.class));
        FileParameterHelperException thrown = assertThrows(
            FileParameterHelperException.class,
            () -> mocked.withTemplateFileName(TEMPLATE_FILE_NAME).readFileContents()
        );
        assertEquals(thrown.getMessage(),"Error while reading file.");
        assertEquals(thrown.getFileName(), TEMPLATE_FILE_NAME);
    }
}
