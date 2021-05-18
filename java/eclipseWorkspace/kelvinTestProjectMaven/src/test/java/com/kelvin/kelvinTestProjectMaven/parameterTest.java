package com.kelvin.kelvinTestProjectMaven;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class parameterTest {
	
	private int input1;  
    private int input2;  
    private int expected;
    
    public parameterTest(int input1,int input2,int expected){  
        this.input1 = input1;  
        this.input2 = input2;  
        this.expected = expected;  
    } 
	
	@Parameters  
    @SuppressWarnings("unchecked")  
    public static Collection prepareData(){  
        Object [][] object = 
        	{
        		{-1,-2,-3},
        		{0,2,2},
        		{-1,1,9},
        		{1,2,3}
        	};  
        return Arrays.asList(object);  
    } 

	@Test  
    public void testAdd(){  
//        Add add = new Add();  
//        int result = add.add(input1, input2);
		int result = input1 + input2;
        Assert.assertEquals(expected,result);  
    }
}
