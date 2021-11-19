package com.tuanmhoang.study.tdd.helper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;

public class CliParameterHelper implements ParameterHelper {

    private Map<String, String> params;

    private final InputStream inputStream;

    /**
     * Number of parameters parts.
     */
    private static final int NUMBER_OF_PARAM_PARTS = 2;

    public CliParameterHelper(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Map<String, String> getParams() {
        readParams();
        return params;
    }

    @Override
    public String getTemplateText() {
        return "To: #{address}\n"
            + "Hello #{user},\n"
            + "Today we are studying #{moduleName}.\n"
            + "This message is generated based on the CONSOLE template.";
    }

    @Override
    public void readParams() {

        try (Scanner scanner = new Scanner(this.inputStream, String.valueOf(StandardCharsets.UTF_8))) {
            System.out.format("Enter parameters for template in format parameterName=parameterValue%n"
                + "end entering parameters with new line %n");
            String parameterLine;
            this.params = new HashMap<>();
            while (scanner.hasNextLine()) {
                parameterLine = scanner.nextLine();
                if (parameterLine.isEmpty()) {
                    break;
                }
                final String[] parameterParts = parameterLine.split("=", 2);

                this.params.put(parameterParts[0], parameterParts[1]);
            }
        }


//        try {
//            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//            System.out.println("result: " + result);
//
//
//        } catch (IOException e) {
//            System.err.println("Error while reading params from CONSOLE.");
//        }
//        params = new HashMap<>();
//        params.put("address", "sample@demo.com");
//        params.put("user", "Tuan");
//        params.put("moduleName", "TDD");
    }
}
