package com.tuanmhoang.study.tdd.template;

import com.tuanmhoang.study.tdd.helper.ParameterHelper;

/**
 * The type Template.
 */
public class Template {

    private final ParameterHelper parameterHelper;

    public Template(ParameterHelper parameterHelper){
        this.parameterHelper = parameterHelper;
    }

    public ParameterHelper getParameterHelper() {
        return parameterHelper;
    }

}
