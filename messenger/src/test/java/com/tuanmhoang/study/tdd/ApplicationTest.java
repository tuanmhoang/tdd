package com.tuanmhoang.study.tdd;

import com.tuanmhoang.study.tdd.cli.CliArgument;
import com.tuanmhoang.study.tdd.cli.CliHelper;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import com.tuanmhoang.study.tdd.helper.FileParameterHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationTest {
    /**
     * Application
     */
    private Application app;

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
        app = new Application();
        app.run(args);
        assertTrue(app.getParameterHelper() instanceof CliParameterHelper);
    }

    @Test
    public void getParameterHelper_shouldReturnFileParameterHelper() {
        String[] args = {CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt",
            CliArgument.OUTPUT_FILE_ARG.getParamName(),"output.txt"
        };
        app = new Application();
        app.run(args);
        assertTrue(app.getParameterHelper() instanceof FileParameterHelper);
    }
}
