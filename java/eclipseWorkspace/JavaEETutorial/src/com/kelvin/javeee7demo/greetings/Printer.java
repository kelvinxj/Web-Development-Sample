package com.kelvin.javeee7demo.greetings;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Printer {

    @Inject 
    //if comment this out, will show: Hello, yourname
    @Informal  
    Greeting greeting;
    
    private String name;
    private String salutation;

    public void createSalutation() {
        this.salutation = greeting.greet(name);
    }

    public String getSalutation() {
        return salutation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}