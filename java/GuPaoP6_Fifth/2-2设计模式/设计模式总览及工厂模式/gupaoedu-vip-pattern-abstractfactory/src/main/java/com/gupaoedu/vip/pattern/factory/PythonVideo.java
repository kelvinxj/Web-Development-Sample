package com.gupaoedu.vip.pattern.factory;

import com.gupaoedu.vip.pattern.factory.IVideo;

/**
 * Created by Tom.
 */
public class PythonVideo implements IVideo {
    public void record() {
        System.out.println("录制Python视频");
    }
}
