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
        fileHelper = new FileHelper();
        AppMode appMode = cliHelper.decideMode(args);
        if (appMode.equals(AppMode.CONSOLE)){
            this.parameterHelper = new CliParameterHelper();
            this.mailServer = new MailServerCli();
            //this.parameterHelper = new CliParameterHelper();
            //this.template = new TemplateCli();
        } else {
            this.parameterHelper = new FileParameterHelper(fileHelper);
            this.mailServer = new MailServerFile();
            //this.parameterHelper = new FileParameterHelper();
            //this.template = new TemplateFile();
        }
        final Client client = new Client(sampleAddress);
        Template template = new Template(parameterHelper);
        TemplateEngine templateEngine = new TemplateEngine();
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
