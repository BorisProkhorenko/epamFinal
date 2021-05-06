package com.epam.web.filter;


import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;


public class XssRequestWrapper extends HttpServletRequestWrapper {

    private final static String SCRIPT_FRAGMENTS = "<script>(.*?)</script>";
    private final static String SRC = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";
    private final static String ANOTHER_SRC = "src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";
    private final static String SCRIPT_OPEN_TAG = "<script(.*?)>";
    private final static String SCRIPT_CLOSE_TAG = "</script>";
    private final static String EVAL = "eval\\((.*?)\\)";
    private final static String EXPRESSION = "expression\\((.*?)\\)";
    private final static String JAVASCRIPT = "javascript:";
    private final static String VBSCRIPT = "vbscript:";
    private final static String ONLOAD = "onload(.*?)=";
    private final static String REPLACE = "\0";
    private final static String EMPTY = "";

    private static final Pattern[] patterns = new Pattern[]{
            Pattern.compile(SCRIPT_FRAGMENTS, Pattern.CASE_INSENSITIVE),
            Pattern.compile(SRC, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(ANOTHER_SRC, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(SCRIPT_CLOSE_TAG, Pattern.CASE_INSENSITIVE),
            Pattern.compile(SCRIPT_OPEN_TAG, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(EVAL, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(EXPRESSION, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(JAVASCRIPT, Pattern.CASE_INSENSITIVE),
            Pattern.compile(VBSCRIPT, Pattern.CASE_INSENSITIVE),
            Pattern.compile(ONLOAD, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    public XssRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXss(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return stripXss(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXss(value);
    }

    private String stripXss(String value) {
        if (value != null) {


            value = value.replaceAll(REPLACE, EMPTY);

            for (Pattern scriptPattern : patterns) {
                value = scriptPattern.matcher(value).replaceAll(EMPTY);
            }

            value = StringEscapeUtils.escapeHtml(value);

        }
        return value;
    }
}
