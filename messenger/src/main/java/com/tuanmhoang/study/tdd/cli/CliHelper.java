package com.tuanmhoang.study.tdd.cli;

import com.tuanmhoang.study.tdd.mode.AppMode;

public class CliHelper {

    private static final int REQUIRED_NUMBER_OF_PARAMS = 6;

    public AppMode decideMode(String[] args){
        if(args.length == 0) return AppMode.CONSOLE;
        return  AppMode.FILE;
    }
}
