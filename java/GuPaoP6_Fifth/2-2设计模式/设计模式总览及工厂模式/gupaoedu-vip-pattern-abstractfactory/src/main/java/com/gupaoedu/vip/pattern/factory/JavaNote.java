package com.gupaoedu.vip.pattern.factory;

import com.gupaoedu.vip.pattern.factory.INote;

/**
 * Java笔记
 * Created by Tom
 */
public class JavaNote implements INote {

    public void edit() {
        System.out.println("编写Java笔记");
    }
}
