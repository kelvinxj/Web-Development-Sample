package com.kelvin.kelvinTestProjectMaven;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Test;

public class reflectionTest {
    
    @Test
    public void getAllClass() throws IOException{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        assert classLoader != null;
        
        //try to initialize IntWritable class cause NoClassDefFoundError: com.google.common.base.Preconditions
        String packageName = "com.kelvin.test.mapreduce";
        //String packageName = "com.kelvin.test.iotest";
        
        String path = packageName.replace('.', '/');

        Enumeration resources = classLoader.getResources(path);

        List dirs = new ArrayList();

        while (resources.hasMoreElements()) {

            URL resource = (URL) resources.nextElement();

            dirs.add(new File(resource.getFile()));

        }

        ArrayList classes = new ArrayList();

        for (Object directory : dirs) {

            classes.addAll(findClasses((File)directory, packageName));

        }
        
        for(Object obj: classes){
        	System.out.println(((Class)obj).getSimpleName());
        }
    }
    
    private static List<Class> findClasses(File directory, String packageName){
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        try{
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        catch(Exception ex){
        	System.out.println(ex.getStackTrace());
        }
        return classes;
    }

}
