package com.tuanmhoang.study.tdd;

import com.tuanmhoang.study.tdd.cli.CliHelper;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import com.tuanmhoang.study.tdd.helper.FileParameterHelper;
import com.tuanmhoang.study.tdd.helper.ParameterHelper;
import com.tuanmhoang.study.tdd.mail.MailServer;
import com.tuanmhoang.study.tdd.mail.MailServerCli;
import com.tuanmhoang.study.tdd.mail.MailServerFile;
import com.tuanmhoang.study.tdd.mode.AppMode;
import com.tuanmhoang.study.tdd.template.Template;
import com.tuanmhoang.study.tdd.template.TemplateCli;
import com.tuanmhoang.study.tdd.template.TemplateEngine;
import com.tuanmhoang.study.tdd.template.TemplateFile;
import java.io.InputStream;
import java.util.Scanner;

public class Application {

    private MailServer mailServer;

    private CliHelper cliHelper;

    private ParameterHelper parameterHelper;

    private Template template;

    private String sampleAddress = "johndoe@myapp.com";

    /**
     * Main method
     *
     * @param args is the argument of this program
     */
    public static void main(String[] args) {
        final Application main = createApp();
        main.run(args);
    }

    public static Application createApp(){
        return new Application();
    }

    public void run(String[] args) {
        cliHelper = new CliHelper();
        AppMode appMode = cliHelper.decideMode(args);
        if (appMode.equals(AppMode.CONSOLE)){
            this.parameterHelper = new CliParameterHelper();
            this.mailServer = new MailServerCli();
            this.template = new TemplateCli();
        } else {
            this.parameterHelper = new FileParameterHelper();
            this.mailServer = new MailServerFile();
            this.template = new TemplateFile();
        }
        final Client client = new Client(sampleAddress);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.generateMessage(template,client);
    }

    public ParameterHelper getParameterHelper() {
        return parameterHelper;
    }

    public MailServer getMailServer(){
        return mailServer;
    }

    public Template getTemplate(){
        return template;
    }
}
