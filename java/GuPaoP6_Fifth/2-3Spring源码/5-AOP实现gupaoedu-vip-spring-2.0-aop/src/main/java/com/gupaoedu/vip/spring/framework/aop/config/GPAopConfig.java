package com.gupaoedu.vip.spring.framework.aop.config;

import lombok.Data;

/**
 * Created by Tom.
 */
@Data
public class GPAopConfig {
    private String pointCut;
    private String aspectClass;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;
}
