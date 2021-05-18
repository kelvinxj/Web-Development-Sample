package com.gupaoedu.vip.pattern.factory;

import com.gupaoedu.vip.pattern.factory.INote;

/**
 * Python笔记
 * Created by Tom.
 */
public class PythonNote implements INote {

    public void edit() {
        System.out.println("编写Python笔记");
    }
}
