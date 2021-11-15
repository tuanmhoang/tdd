package com.tuanmhoang.study.tdd.helper.file;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

public class FileHelper {

    private String templateFileName;

    public String readFileContents() throws IOException, FileParameterHelperException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(templateFileName);
        if(resource == null ) throw new FileParameterHelperException("File does not exist.",templateFileName);

        File file = new File(resource.getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8).replace("\r\n","\n");
        return content;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public FileHelper withTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
        return this;
    }
}
