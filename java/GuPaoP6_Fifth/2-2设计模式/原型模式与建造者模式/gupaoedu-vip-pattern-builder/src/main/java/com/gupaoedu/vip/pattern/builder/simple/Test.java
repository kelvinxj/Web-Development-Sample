package com.gupaoedu.vip.pattern.builder.simple;

/**
 * Created by geely
 */
public class Test {
    public static void main(String[] args) {
        CourseBuilder builder = new CourseBuilder();

        builder.addName("设计模式");
        builder.addPPT("【PPT课件】");
        builder.addVideo("【回放视频】");
        builder.addNote("【课堂笔记】");
        builder.addHomework("【课后作业】");

        System.out.println(builder.build());

    }
}
