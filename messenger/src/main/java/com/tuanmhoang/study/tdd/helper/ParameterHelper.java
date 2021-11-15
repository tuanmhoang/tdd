package com.tuanmhoang.study.tdd.helper;

import com.tuanmhoang.study.tdd.helper.file.FileParameterHelperException;
import java.io.IOException;
import java.util.Map;

/**
 * Container for parameters
 */
public interface ParameterHelper {
    /**
     * Get parameters.
     * @return parameters
     */
    Map<String, String> getParams();

    /**
     * Get template.
     * @return template
     */
    String getTemplateText();

    /**
     * Read parameters.
     */
    void readParams();
}
