package com.tuanmhoang.study.tdd.cli;

import com.tuanmhoang.study.tdd.mode.AppMode;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CliHelperTest {

    private CliHelper cliHelper;

    @Test
    public void withParameterLengthIsZero_should_returnAppModeIsConsole(){
        String[] args = {};
        cliHelper = new CliHelper(args);
        AppMode actualAppMode = cliHelper.decideMode();
        assertEquals(AppMode.CONSOLE,actualAppMode);
    }

    @Test
    public void withParameterLengthIsSixAndCorrect_should_returnAppModeIsFile(){
        String[] args = {CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt",
            CliArgument.OUTPUT_FILE_ARG.getParamName(),"output.txt"
        };
        cliHelper = new CliHelper(args);
        AppMode actualAppMode = cliHelper.decideMode();
        assertEquals(AppMode.FILE,actualAppMode);
    }

    @Test
    public void withParameterLengthIsNotZeroOrSix_shouldThrowException(){
        String[] args = {
            CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt"
        };
        cliHelper = new CliHelper(args);
        CliArgumentException thrown = assertThrows(
            CliArgumentException.class,
            () -> cliHelper.decideMode()
        );

        assertTrue(thrown.getMessage().contains("Number of parameters is wrong"));
        assertEquals(thrown.getNumberOfParameters(), args.length);
    }

    @Test
    public void withInvalidParameter_shouldThrowException(){
        String[] args = {
            "incorrect-params","template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt"
        };
        cliHelper = new CliHelper(args);
        CliArgumentException thrown = assertThrows(
            CliArgumentException.class,
            () -> cliHelper.decideMode()
        );

        assertTrue(thrown.getMessage().contains("Parameters is incorrect"));
        assertEquals(thrown.getNumberOfParameters(), args.length);
    }

    @Test
    public void getOutput_shouldSuccess(){
        String[] args = {CliArgument.TEMPLATE_FILE_ARG.getParamName(),"template.txt",
            CliArgument.PARAMS_FILE_ARG.getParamName(),"params.txt",
            CliArgument.OUTPUT_FILE_ARG.getParamName(),"output.txt"
        };
        cliHelper = new CliHelper(args);
        String outputFile = cliHelper.getOutput();
        assertEquals("output.txt",outputFile);
    }
}
