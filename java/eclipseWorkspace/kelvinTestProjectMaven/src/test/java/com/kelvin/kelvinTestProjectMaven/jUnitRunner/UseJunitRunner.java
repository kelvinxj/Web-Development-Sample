package com.kelvin.kelvinTestProjectMaven.jUnitRunner;

import com.kelvin.kelvinTestProjectMaven.BasicTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

public class UseJunitRunner{
    public static void main(String[] args) {
        //Use JUnit runner: JUnitCore
        System.out.println("Hello, JUnitCore class");

        Long start = System.currentTimeMillis();
        Result result = JUnitCore.runClasses(BasicTest.class);
        List<Failure> failures = result.getFailures();
        Long end = System.currentTimeMillis();
        System.out.println(failures);
//		System.out.println("Total time(sec): " + (end - start)/1000.0);
    }
}
