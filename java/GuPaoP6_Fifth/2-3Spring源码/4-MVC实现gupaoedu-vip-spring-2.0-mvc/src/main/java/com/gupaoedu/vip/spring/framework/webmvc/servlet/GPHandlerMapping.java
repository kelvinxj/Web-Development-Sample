package com.gupaoedu.vip.spring.framework.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class GPHandlerMapping {

    private Object controller;
    protected Method method;
    protected Pattern pattern;

    public GPHandlerMapping(Pattern pattern,Object controller,Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Object getController() {
        return controller;
    }
}
