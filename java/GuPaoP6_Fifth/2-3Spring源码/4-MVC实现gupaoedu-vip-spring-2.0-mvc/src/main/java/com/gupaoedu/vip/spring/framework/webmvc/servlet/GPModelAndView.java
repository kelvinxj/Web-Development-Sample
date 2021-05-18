package com.gupaoedu.vip.spring.framework.webmvc.servlet;

import java.util.Map;

public class GPModelAndView {
    private String viewName;
    private Map<String,?> model;

    public GPModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public GPModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}
