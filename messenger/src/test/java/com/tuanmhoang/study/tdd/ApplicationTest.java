package com.tuanmhoang.study.tdd;

import com.tuanmhoang.study.tdd.cli.CliHelper;
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
    public void getParameterHelper_shouldReturnCliHelper() {
        String[] args = {""};
        app.run(args);
        assertTrue(app.getParameterHelper() instanceof CliHelper);
    }
}
