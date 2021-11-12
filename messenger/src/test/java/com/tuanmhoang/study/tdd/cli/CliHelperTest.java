package com.tuanmhoang.study.tdd.cli;

import com.tuanmhoang.study.tdd.mode.AppMode;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CliHelperTest {

    private CliHelper cliHelper = new CliHelper();

    @Test
    public void withParameterLengthIsZero_should_returnAppModeIsConsole(){
        String[] args = {};
        AppMode actualAppMode = cliHelper.decideMode(args);
        assertEquals(AppMode.CONSOLE,actualAppMode);
    }

    @Test
    public void withParameterLengthIsSixAndCorrect_should_returnAppModeIsFile(){
        String[] args = {CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt",
            CliArgument.OUTPUT_FILE_ARG.getParamName(),"output.txt"
        };
        AppMode actualAppMode = cliHelper.decideMode(args);
        assertEquals(AppMode.FILE,actualAppMode);
    }

    @Test
    public void withParameterLengthIsNotZeroOrSix_shouldThrowException(){
        String[] args = {
            CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt"
        };

        CliArgumentException thrown = assertThrows(
            CliArgumentException.class,
            () -> cliHelper.decideMode(args)
        );

        assertTrue(thrown.getMessage().contains("Number of parameters is wrong"));
        assertEquals(thrown.getNumberOfParameters(), args.length);
    }

    @Test
    public void withInvalidParameter_should_throwException(){
        fail();
    }

}
