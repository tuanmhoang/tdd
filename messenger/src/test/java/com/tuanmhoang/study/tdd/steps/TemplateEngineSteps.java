package com.tuanmhoang.study.tdd.steps;

import com.tuanmhoang.study.tdd.Client;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import com.tuanmhoang.study.tdd.helper.FileParameterHelper;
import com.tuanmhoang.study.tdd.helper.ParameterHelper;
import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.template.Template;
import com.tuanmhoang.study.tdd.template.TemplateEngine;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateEngineSteps {

    private TemplateEngine templateEngine = new TemplateEngine();

    private String generatedMessageFromConsole = null;

    private String generatedMessageFromFile = null;

    private Template template;

    private Client client;

    private Map<String, String> paramsConsole = new HashMap<>();

    private Map<String, String> paramsFile = new HashMap<>();

    private ParameterHelper parameterHelper;

    @Given("Template generate from Console")
    public void template_generate_from_console() {
        System.out.println("CONSOLE mode is selected");
    }

    @Given("user param is {string}")
    public void user_param_is(String user) {
        paramsConsole.put("user", user);
    }

    @Given("module param is {string}")
    public void module_param_is(String moduleName) {
        paramsConsole.put("moduleName", moduleName);
    }

    @Given("address param is {string}")
    public void address_param_is(String address) {
        paramsConsole.put("address", address);
    }

    @When("Template Engine generates message")
    public void template_engine_generates_message() {
        ByteArrayInputStream inputStreamCaptor1 = buildInputStreamFromMapParams(paramsConsole);
        System.setIn(inputStreamCaptor1);

        parameterHelper = new CliParameterHelper(System.in);
        client = new Client();

        template = new Template(parameterHelper);
        generatedMessageFromConsole = templateEngine.generateMessage(template, client);
    }

    @Then("Message {string} should be generated")
    public void message_should_be_generated(String expectedMessage) {
        System.out.println("Generated message from CONSOLE:");
        System.out.println(generatedMessageFromConsole);
        assertEquals(expectedMessage.replace("{enter}","\n"), generatedMessageFromConsole);
    }

    private ByteArrayInputStream buildInputStreamFromMapParams(Map<String, String> mapParams) {
        String inputParams1 = mapParams.keySet().stream()
            .map(key -> key + "=" + mapParams.get(key))
            .collect(Collectors.joining(System.lineSeparator(), "", System.lineSeparator()));

        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(inputParams1.getBytes(StandardCharsets.UTF_8));
        return inputStreamCaptor;
    }

    ///////////////////////////////////////
    @Given("Template generate from File")
    public void template_generate_from_file() {
        System.out.println("FILE mode is selected");
    }

    @Given("user param for file is {string}")
    public void user_param_for_file_is(String user) {
        paramsFile.put("user", user);
    }

    @Given("module param for file is {string}")
    public void module_param_for_file_is(String moduleName) {
        paramsFile.put("moduleName", moduleName);
    }

    @Given("address param for file is {string}")
    public void address_param_for_file_is(String address) {
        paramsFile.put("moduleName", address);
    }

    @When("Template Engine generates message with file mode")
    public void template_engine_generates_message_with_file_mode() {
        FileHelper fileHelper = new FileHelper();
        parameterHelper = new FileParameterHelper(fileHelper.withTemplateFileName("template-study.txt"));
        client = new Client();

        template = new Template(parameterHelper);
        generatedMessageFromFile = templateEngine.generateMessage(template, client);
    }

    @Then("Message {string} should be generated from file mode")
    public void message_should_be_generated_from_file_mode(String expectedMessage) {
        System.out.println("Generated message from FILE:");
        System.out.println(generatedMessageFromFile);
        assertEquals(expectedMessage.replace("{enter}","\n"), generatedMessageFromFile);
    }
}
