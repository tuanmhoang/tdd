package com.tuanmhoang.study.tdd;

import com.tuanmhoang.study.tdd.cli.CliHelper;
import com.tuanmhoang.study.tdd.helper.CliParameterHelper;
import com.tuanmhoang.study.tdd.helper.FileParameterHelper;
import com.tuanmhoang.study.tdd.helper.ParameterHelper;
import com.tuanmhoang.study.tdd.helper.file.FileHelper;
import com.tuanmhoang.study.tdd.mail.MailServer;
import com.tuanmhoang.study.tdd.mail.MailServerCli;
import com.tuanmhoang.study.tdd.mail.MailServerFile;
import com.tuanmhoang.study.tdd.mode.AppMode;
import com.tuanmhoang.study.tdd.template.Template;
import com.tuanmhoang.study.tdd.template.TemplateEngine;

public class Application {

    private MailServer mailServer;

    private CliHelper cliHelper;

    private FileHelper fileHelper;

    private ParameterHelper parameterHelper;

    private static TemplateEngine templateEngine;

    private String sampleAddress = "johndoe@myapp.com";

    /**
     * Main method
     *
     * @param args is the argument of this program
     */
    public static void main(String[] args) {
        final Application main = new Application(templateEngine);
        main.run(args);
    }

    public Application(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

//    public static Application createApp(){
//        Application app = new Application();
//        return app;
//    }

    /**
     * Run application
     * @param args arguments of the program
     */
    public void run(String[] args) {
        cliHelper = new CliHelper();
        fileHelper = new FileHelper();
        AppMode appMode = cliHelper.decideMode(args);
        if (appMode.equals(AppMode.CONSOLE)){
            this.parameterHelper = new CliParameterHelper(System.in);
            this.mailServer = new MailServerCli();
        } else {
            this.parameterHelper = new FileParameterHelper(fileHelper);
            this.mailServer = new MailServerFile();
        }
        final Client client = new Client(sampleAddress);
        Template template = new Template(parameterHelper);
        String generatedMessage = templateEngine.generateMessage(template,client);
        System.out.println("generatedMessage: "+ generatedMessage);
    }

    public ParameterHelper getParameterHelper() {
        return parameterHelper;
    }

    public MailServer getMailServer(){
        return mailServer;
    }

}
