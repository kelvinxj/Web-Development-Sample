package com.gupaoedu.vip.spring.framework.beans;

public class GPBeanWrapper {
    private Object wrapperedInstance;
    private Class<?> wrappedClass;

    public GPBeanWrapper(Object instance) {
        this.wrapperedInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrappedInstance(){
        return this.wrapperedInstance;
    }

    public Class<?> getWrappedClass(){
        return this.wrappedClass;
    }
}
