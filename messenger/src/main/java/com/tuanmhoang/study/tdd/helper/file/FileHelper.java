package com.tuanmhoang.study.tdd.helper.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class FileHelper {

    private String templateFileName;

    private static final String EXPECTED_FILE_CONTENT = "To: #{address}\n"
        + "Hello #{user}!\n"
        + "Today we are studying #{moduleName}\n"
        + "This message is generated based on the FILE template.";

    public String readFileContents() throws IOException, FileParameterHelperException {

        return EXPECTED_FILE_CONTENT;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public FileHelper withTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
        return this;
    }
}
