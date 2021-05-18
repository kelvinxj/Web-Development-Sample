package com.gupaoedu.vip.pattern.flyweight.ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class TicketFactory {
    private static Map<String, ITicket> sTicketPool = new ConcurrentHashMap<String,ITicket>();

    public static ITicket queryTicket(String from, String to) {
        String key = from + "->" + to;
        if (TicketFactory.sTicketPool.containsKey(key)) {
            System.out.println("使用缓存：" + key);
            return TicketFactory.sTicketPool.get(key);
        }
        System.out.println("首次查询，创建对象: " + key);
        ITicket ticket = new TrainTicket(from, to);
        TicketFactory.sTicketPool.put(key, ticket);
        return ticket;
    }
}