package com.tuanmhoang.study.tdd;

import com.tuanmhoang.study.tdd.cli.CliArgument;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import com.tuanmhoang.study.tdd.helper.FileParameterHelper;
import com.tuanmhoang.study.tdd.mail.MailServerCli;
import com.tuanmhoang.study.tdd.mail.MailServerFile;
import com.tuanmhoang.study.tdd.template.TemplateEngine;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApplicationTest {
    /**
     * Application
     */
    private Application app;

    private TemplateEngine mockedTemplateEngine = mock(TemplateEngine.class);

    @BeforeEach
    public void init(){
        doReturn("mocked message").when(mockedTemplateEngine).generateMessage(any(), any());
    }

    @Test
    public void applicationRun_shouldBeCalled_success() {
        String[] args = {""};
        app = mock(Application.class);
        app.run(args);
        verify(app).run(args);
    }

    @Test
    public void getParameterHelper_shouldReturnCliParameterHelper() {
        String[] args = {};
        app = new Application(mockedTemplateEngine);
        app.run(args);
        assertTrue(app.getParameterHelper() instanceof CliParameterHelper);
    }

    @Test
    public void getParameterHelper_shouldReturnFileParameterHelper() {
        String[] args = {CliArgument.TEMPLATE_FILE_ARG.getParamName(), "template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(), "params.txt",
            CliArgument.OUTPUT_FILE_ARG.getParamName(), "output.txt"
        };
        app = new Application(mockedTemplateEngine);
        app.run(args);
        assertTrue(app.getParameterHelper() instanceof FileParameterHelper);
    }

    @Test
    public void getMailServer_shouldReturnMailServerCli() {
        String[] args = {};
        app = new Application(mockedTemplateEngine);
        app.run(args);
        assertTrue(app.getMailServer() instanceof MailServerCli);
    }

    @Test
    public void getMailServer_shouldReturnMailServerFile() {
        String[] args = {CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt",
            CliArgument.OUTPUT_FILE_ARG.getParamName(),"output.txt"
        };
        app = new Application(mockedTemplateEngine);
        app.run(args);
        assertTrue(app.getMailServer() instanceof MailServerFile);
    }

}
