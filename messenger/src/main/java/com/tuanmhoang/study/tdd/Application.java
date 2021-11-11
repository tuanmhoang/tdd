package com.tuanmhoang.study.tdd;

import com.tuanmhoang.study.tdd.cli.CliHelper;
import java.io.InputStream;
import java.util.Scanner;

public class Application {

    private MailServer mailServer;

    private CliHelper cliHelper;

    /**
     * Main method
     *
     * @param args is the argument of this program
     */
    public static void main(String[] args) {
        final Application main = new Application();
        main.run(args);

    }

    public void run(String[] args) {
        cliHelper = new CliHelper();

    }

}
