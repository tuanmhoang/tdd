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
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The application to send email
 */
public class Application {

    private MailServer mailServer;

    private CliHelper cliHelper;

    private FileHelper fileHelper;

    private ParameterHelper parameterHelper;

    private TemplateEngine templateEngine;

    private String sampleAddress = "johndoe@myapp.com";

    /**
     * Main method
     *
     * @param args is the argument of this program
     */
    public static void main(String[] args) {
        final Application main = new Application();
        main.run(args);
    }

    /**
     * Run application
     *
     * @param args arguments of the program
     */
    public void run(String[] args) {
        cliHelper = new CliHelper(args);
        fileHelper = new FileHelper();
        AppMode appMode = cliHelper.decideMode();
        if (appMode.equals(AppMode.CONSOLE)) {
            this.parameterHelper = new CliParameterHelper(System.in);
            this.mailServer = new MailServerCli(System.out);
        } else {
            this.parameterHelper = new FileParameterHelper(fileHelper);
            final Path outputFile = Paths.get(cliHelper.getOutput());
            this.mailServer = new MailServerFile(outputFile);
        }
        final Client client = new Client(sampleAddress);
        Template template = new Template(parameterHelper);
        templateEngine = getTemplateEngine();
        String generatedMessage = templateEngine.generateMessage(template, client);
        //System.out.println("generatedMessage: "+ generatedMessage);
        mailServer.send(client.getAddresses(), generatedMessage);
    }

    public ParameterHelper getParameterHelper() {
        return parameterHelper;
    }

    public MailServer getMailServer() {
        return mailServer;
    }

    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * Get the template engine
     *
     * @return template engine
     */
    public TemplateEngine getTemplateEngine() {
        if (this.templateEngine == null) {
            return new TemplateEngine();
        }
        return templateEngine;
    }
}
