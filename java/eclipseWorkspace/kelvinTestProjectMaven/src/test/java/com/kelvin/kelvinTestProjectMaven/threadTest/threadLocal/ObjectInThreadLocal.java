package com.kelvin.kelvinTestProjectMaven.threadTest.threadLocal;

public class ObjectInThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<InfoObject> tlObject = new ThreadLocal<>();
        InfoObject info = new InfoObject(1,"info1");
//        ThreadLocal<InfoObject> tlObject = new ThreadLocal<InfoObject>(){
//            @Override
//            protected InfoObject initialValue() {
//                return info;
//            }
//        };


        for(int i = 0;i < 3;i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    //all thread use the same object, this is not isolation.
                    tlObject.set(info);
                    InfoObject obj = tlObject.get();
                    int init = obj.getId();
                    for(int i = 0;i<5;i++){
                        init++;
                    }
                    obj.setId(init);
                    System.out.println("Thread name:" + this.getName() + ",id="
                            + obj.getId()
                            + ", I get object: "
                            + obj);
                }
            };
            t.start();
        }
    }
}
