package com.gupaoedu.vip.pattern.factory.abstractfactory;

import com.gupaoedu.vip.pattern.factory.INote;
import com.gupaoedu.vip.pattern.factory.IVideo;
import com.gupaoedu.vip.pattern.factory.PythonNote;
import com.gupaoedu.vip.pattern.factory.PythonVideo;

/**
 * Created by Tom.
 */
public class PythonCourseFactory extends CourseFactory {

    public INote createNote() {
        super.init();
        return new PythonNote();
    }


    public IVideo createVideo() {
        super.init();
        return new PythonVideo();
    }
}
