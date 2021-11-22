package com.tuanmhoang.study.tdd.cli;

import com.tuanmhoang.study.tdd.mode.AppMode;
import java.util.Arrays;
import java.util.List;

public class CliHelper {

    private static final int REQUIRED_NUMBER_OF_PARAMS = 6;

    /**
     * Decides which mode should be used
     * @param args arguments to decide the mode
     * @return the mode based on the arguments
     */
    public AppMode decideMode(String[] args) {
        final int argsLength = args.length;

        if (argsLength == 0) {
            return AppMode.CONSOLE;
        }

        if (argsLength != 0 && argsLength != REQUIRED_NUMBER_OF_PARAMS) {
            throw new CliArgumentException("Number of parameters is wrong", argsLength);
        }

        if (isArgsInvalid(args)) {
            throw new CliArgumentException("Parameters is incorrect", argsLength);
        }

        return AppMode.FILE;
    }

    private boolean isArgsValid(String[] args) {
        List<String> argsAsList = Arrays.asList(args);
        if (argsAsList.contains(CliArgument.PARAMS_FILE_ARG.getParamName())
            && argsAsList.contains(CliArgument.TEMPLATE_FILE_ARG.getParamName())
            && argsAsList.contains(CliArgument.OUTPUT_FILE_ARG.getParamName())
        ) {
            return true;
        }
        return false;
    }

    private boolean isArgsInvalid(String[] args) {
        return !isArgsValid(args);
    }
}
