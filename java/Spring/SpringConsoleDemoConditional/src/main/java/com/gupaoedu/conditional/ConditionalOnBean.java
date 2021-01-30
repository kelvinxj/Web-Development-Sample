package com.gupaoedu.conditional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalOnBean implements Condition {
    @Override
    /**
     * true means IoC container load bean.
     * false means not load bean
     *
     * return true where there is person bean
     * this always return true because person bean defined before user bean in JavaConfig class
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean hasPerson = context.getRegistry().containsBeanDefinition("person");
        return hasPerson;
    }
}
