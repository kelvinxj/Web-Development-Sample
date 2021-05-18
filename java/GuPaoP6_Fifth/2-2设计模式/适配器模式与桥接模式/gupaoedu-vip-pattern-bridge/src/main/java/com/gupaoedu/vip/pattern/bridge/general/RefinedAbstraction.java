package com.gupaoedu.vip.pattern.bridge.general;

// 修正抽象
public class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(IImplementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("refined operation");
    }
}