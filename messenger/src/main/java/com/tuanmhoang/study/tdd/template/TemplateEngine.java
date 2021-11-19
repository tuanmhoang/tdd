package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.Client;
import com.tuanmhoang.study.tdd.helper.ParameterHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * regex for variable in template e.g. '#{someVariable}'
     */
    private static final Pattern parameterPattern = Pattern.compile("#\\{(\\S+)\\}");

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        ParameterHelper parameterHelper = template.getParameterHelper();
        String templateText = parameterHelper.getTemplateText();

        final Matcher parameterMatcher = parameterPattern.matcher(templateText);
        Map<String,String> params = parameterHelper.getParams();

        final StringBuffer result = new StringBuffer();
        while (parameterMatcher.find()) {
            final String parameterName = parameterMatcher.group(1);
            parameterMatcher.appendReplacement(result, params.get(parameterName));
        }
        parameterMatcher.appendTail(result);

        return result.toString();
    }
}
