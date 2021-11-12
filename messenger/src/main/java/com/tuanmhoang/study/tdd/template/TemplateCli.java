package com.tuanmhoang.study.tdd.template;

public class TemplateCli implements Template{
    @Override
    public String getMessage() {
        return "To: #{client}/n"
            + "Hello #{user}!/n"
            + "Today we are studying #{moduleName}/n"
            + "This message is generated in CONSOLE mode.";
    }
}
