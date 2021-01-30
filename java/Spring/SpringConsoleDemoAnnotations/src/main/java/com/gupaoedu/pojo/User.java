package com.gupaoedu.pojo;

import com.gupaoedu.pojo.demo.GpImportSelector;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
public class User {
    public User(){
        System.out.println("User initialized...");
    }
}
