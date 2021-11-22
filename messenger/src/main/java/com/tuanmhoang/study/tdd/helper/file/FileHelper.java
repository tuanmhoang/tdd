package com.tuanmhoang.study.tdd.helper.file;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class FileHelper {

    private String templateFileName;

    /**
     * Read file contents
     *
     * @return String of contents
     *
     * @throws FileParameterHelperException when file does not exist
     */
    public String readFileContents() throws FileParameterHelperException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(templateFileName);
//        System.out.println("[1111111] ");
//        System.out.println("[1] resource: "+ resource.toString());
//        final URL resource1 = Thread.currentThread().getContextClassLoader().getResource("myresource.xml");
//        System.out.println("[1] resource1: "+ resource1.toString());
        if (resource == null) {
            throw new FileParameterHelperException("File does not exist.", templateFileName);
        }

        File file = new File(resource.getFile());
        String content = null;
        try {
            content = readFileAsString(file);
        } catch (IOException e) {
            throw new FileParameterHelperException("Error while reading file.", templateFileName);
        }
        return content;
    }

    /**
     * Read the file as string
     * @param file the file to read
     * @return String content of file
     * @throws IOException when exception
     */
    public String readFileAsString(File file) throws IOException {
//        System.out.println("[1]");
//        Path pathaaaa = Paths.get(file.getName());
//        final String path = file.getAbsolutePath();
//        System.out.println("[2]"+ pathaaaa);
//
//        final URL resource = ClassLoader.getSystemClassLoader().getResource("template-study.txt");
//        System.out.println("[2] resource: "+ resource);
//        System.out.println("[3]"+file.getAbsolutePath());
//        System.out.println("[********]");
        //return Files.readString(Path.of(file.getPath()), StandardCharsets.UTF_8).replace("\r\n", "\n");
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public FileHelper withTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
        return this;
    }

}
