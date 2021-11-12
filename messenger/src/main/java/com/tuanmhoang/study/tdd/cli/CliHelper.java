package com.tuanmhoang.study.tdd.cli;

import com.tuanmhoang.study.tdd.mode.AppMode;

public class CliHelper {

    private static final int REQUIRED_NUMBER_OF_PARAMS = 6;

    public AppMode decideMode(String[] args) {

        final int argsLength = args.length;

        if (argsLength != 0 || argsLength != REQUIRED_NUMBER_OF_PARAMS) {
            throw new CliArgumentException("Number of parameters is wrong", argsLength);
        }
        if (argsLength == 0) {
            return AppMode.CONSOLE;
        }
        return AppMode.FILE;
    }
}
