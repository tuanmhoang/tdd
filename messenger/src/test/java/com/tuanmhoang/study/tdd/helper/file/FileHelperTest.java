package com.tuanmhoang.study.tdd.helper.file;

import com.tuanmhoang.study.tdd.cli.CliArgumentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHelperTest {
    private FileHelper fileHelper = new FileHelper();

    private static final String EXPECTED_FILE_CONTENT ="To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    @Test
    public void readFileContent_shouldSuccess() throws IOException, URISyntaxException {
        String fileContents = fileHelper.readFileContents();
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
        assertTrue(thrown.getMessage().equals("File InputStream is null."));
        assertEquals(thrown.getFileName(), notExistFileName);
    }
}
