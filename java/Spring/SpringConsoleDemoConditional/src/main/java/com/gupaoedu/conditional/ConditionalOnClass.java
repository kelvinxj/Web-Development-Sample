package com.gupaoedu.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalOnClass implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try{
            Class<?> aClass = context.getClassLoader().loadClass("com.gupaoedu.test.test1");
            return aClass != null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
