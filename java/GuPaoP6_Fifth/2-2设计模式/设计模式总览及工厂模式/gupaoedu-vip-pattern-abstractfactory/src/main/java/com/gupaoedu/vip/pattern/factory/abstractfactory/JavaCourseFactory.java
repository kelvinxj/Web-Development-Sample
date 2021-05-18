package com.gupaoedu.vip.pattern.factory.abstractfactory;

import com.gupaoedu.vip.pattern.factory.INote;
import com.gupaoedu.vip.pattern.factory.IVideo;
import com.gupaoedu.vip.pattern.factory.JavaNote;
import com.gupaoedu.vip.pattern.factory.JavaVideo;

/**
 * Created by Tom.
 */
public class JavaCourseFactory extends CourseFactory {

    public INote createNote() {
        super.init();
        return new JavaNote();
    }

    public IVideo createVideo() {
        super.init();
        return new JavaVideo();
    }
}
