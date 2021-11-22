package com.tuanmhoang.study.tdd.mail;

import java.io.PrintStream;

public class MailServerCli implements MailServer{

    /**
     * Output stream for message.
     */
    private final PrintStream out;

    /**
     *
     * @param out output stream
     */
    public MailServerCli(final PrintStream out) {
        this.out = out;
    }

    @Override
    public void send(String address, String content) {
        out.format("to: %s%n%s", address, content);
    }

}
