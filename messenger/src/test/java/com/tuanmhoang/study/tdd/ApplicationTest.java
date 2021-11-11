package com.tuanmhoang.study.tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
public class ApplicationTest {
    /**
     * Application
     */
    private Application app = mock(Application.class);

    @Test
    public void applicationRun_shouldBeCalled_success(){
        String[] args = {""};
        verify(app).run(args);
    }
}
