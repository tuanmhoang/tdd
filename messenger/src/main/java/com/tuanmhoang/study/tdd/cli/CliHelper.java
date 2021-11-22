package com.tuanmhoang.study.tdd.cli;

import com.tuanmhoang.study.tdd.mode.AppMode;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CliHelper {

    private static final int REQUIRED_NUMBER_OF_PARAMS = 6;

    private final String[] args;

    public CliHelper(final String... args) {
        this.args = args;
    }

    /**
     * Decides which mode should be used
     *
     * @return the mode based on the arguments
     */
    public AppMode decideMode() {
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

    /**
     * Get output file name.
     *
     * @return value of output
     */
    public String getOutput() {
        final Map<CliArgument, String> arguments = new EnumMap<>(CliArgument.class);
        for (int i = 0; i < args.length; i++) {
            final int finalI = i;
            if (finalI % 2 == 0) {
                Arrays.stream(CliArgument.values())
                    .filter(arg -> arg.getParamName().equals(args[finalI]))
                    .forEach(arg -> arguments.put(arg, args[finalI + 1]));
            }
        }
        if (arguments.size() != CliArgument.values().length) {
            throw new CliArgumentException("Names of parameters are wrong", arguments.size());
        }
        return arguments.get(CliArgument.OUTPUT_FILE_ARG);
    }

    private boolean isArgsInvalid(String[] args) {
        return !isArgsValid(args);
    }
}
