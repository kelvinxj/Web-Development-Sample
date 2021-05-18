package com.gupaoedu.vip.pattern.strategy.general;

//上下文环境
public class Context {
    private IStrategy mStrategy;

    public Context(IStrategy strategy) {
        this.mStrategy = strategy;
    }

    public void algorithm() {
        this.mStrategy.algorithm();
    }
}