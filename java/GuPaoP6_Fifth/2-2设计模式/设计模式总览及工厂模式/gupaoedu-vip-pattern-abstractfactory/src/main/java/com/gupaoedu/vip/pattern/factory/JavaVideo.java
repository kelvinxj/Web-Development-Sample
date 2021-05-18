package com.gupaoedu.vip.pattern.factory;


import com.gupaoedu.vip.pattern.factory.IVideo;

/**
 * Created by Tom on.
 */
public class JavaVideo implements IVideo {
    public void record() {
        System.out.println("录制Java视频");
    }
}
