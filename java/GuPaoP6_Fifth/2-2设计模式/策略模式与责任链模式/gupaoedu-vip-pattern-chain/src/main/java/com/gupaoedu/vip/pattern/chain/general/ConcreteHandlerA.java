package com.gupaoedu.vip.pattern.chain.general;

public class ConcreteHandlerA extends Handler {

    public void handleRequest(String request) {
        if ("requestA".equals(request)) {
            System.out.println(this.getClass().getSimpleName() + "deal with request: " + request);
            return;
        }
        if (this.nextHandler != null) {
            this.nextHandler.handleRequest(request);
        }
    }
}