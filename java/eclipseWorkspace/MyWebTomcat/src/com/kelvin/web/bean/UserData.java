package com.kelvin.web.bean;

public class UserData {
	//not a recommended way for a bean property
	String Nme8;
	
    String email;
    int age;

    //this setter works but it is not recommended way for naming a setter method
    public void setnme8( String value )
    {
        Nme8 = value;
    }

    public void setEmail( String value )
    {
        email = value;
    }

    public void setAge( int value )
    {
        age = value;
    }

    //this getter works but it is not recommended way for naming a getter method
    public String getnme8() { return Nme8; }

    public String getEmail() { return email; }

    public int getAge() { return age; }
}
