package com.kelvin.kelvinTestProjectMaven;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaFundmental {
	public static void main (String[] args){
		//Basic method to display something to console.
		System.out.println("Hello,world!");
		
		//Basic data type in java
		//primitive integer type.
		int a = 1;
		Integer aInt = new Integer(1);
		Integer bInt = new Integer(1);
		
		//Something need to be remembered.
		//when compare two Integer type, can't use ==, should use .equals()
		if(aInt == bInt){
			System.out.println(aInt == bInt);
		}
		else{
			System.out.println("Are you kidding me? " + aInt + " is not equals " + bInt + "?????");
		}
		
		//use equals()
		if(aInt.equals(bInt))
			System.out.println(aInt + " is equals to " + bInt);
		else
			System.out.println("It is impossible");
		
		//float type.
		float b = 1f;
		
		//String type.
		String str = "this is a String";
		String strb = "This IS A String";
		
		//compare two strings. equals method return true only when two strings have same characters.
		System.out.println(str.equals(strb));
		
		//this method ignores case.
		System.out.println(str.equalsIgnoreCase(strb));
		
		//some method of String class
		System.out.println(str + "'s length is:" + str.length());
		
		//make two strings into one.
		//normal way. slow but simple.
		String newStr = str + strb;
		System.out.println("new string is:" + newStr);
		
		//Good way. This makes sure good performance
		StringBuilder sb = new StringBuilder();
		sb.append("first string");
		sb.append("second string");
		System.out.println("new String is:" + sb.toString());
		
		//Some flow control clause.
		if(true){
			System.out.println("it's true");
		}else
			System.out.println("It's not true");
		
		//loop using while
		int i = 5;
		while(i>=0){
			System.out.println(i);
			i--;
		}
		
		//loop using for.
		for(int i1 = 1;i1<8;i1++){
			System.out.println(i1);
		}
		
		//Useful topics. 
		//Convert between String and int/long.
		String strInt = "123";
		int intVal = Integer.valueOf(strInt);
		System.out.println(strInt + "'s int value is:" + intVal);
		
		//Useful topics
		//String format.
		String pattern = "My name is {0}, my country is {1}, it is the best country of the all {2} countries in the world";
		String formattedStr = MessageFormat.format(pattern, "good person", "China", 333);
		System.out.println(formattedStr);
		
		//Advanced topics. Collections
		//list. can have duplicate elements
		List<String> strList = new ArrayList<String>();
		strList.add("China");
		strList.add("China");
		strList.add("other country");
		
		System.out.println("strList has " + strList.size() + " elements, they are:");
		
		//iterate all elements in list:
		//method 1. basic method with least code.
		System.out.println("Iteration using method 1:");
		for(String str1 : strList){
			System.out.println(str1);
		}
		
		//method 2. also basic method.
		System.out.println("Iteration using method 2:");
		for(int j = 0;j<strList.size();j++){
			System.out.println(strList.get(j));
		}
		
		//method 3. advanced method, use iterator.
		//Iterator<E> is the interface of collection class.
		System.out.println("Iteration using method 3:");
		Iterator<String> iterator = strList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		
		//Set. only has distinct elements
		Set<String> strSet = new HashSet<String>();
		strSet.add("china");
		
		//add duplicate elements will return false. so this code has no effect.
		strSet.add("china");
		strSet.add("other country");
		
		System.out.println("How many countries in the world?");
		System.out.println("There are " + strSet.size() + ". They are:");
		for(String str2 : strSet){
			System.out.println(str2);
		}
		
		//Map. map's key are distinct. put elements with same key will hide former element
		Map<String, Integer> nameAge = new HashMap<String, Integer>();
		nameAge.put("tom", 28);
		nameAge.put("roger",33);
		
		//add duplicate key, "roger"-33 will be hidden.
		nameAge.put("roger", 44);
		
		//get single element in Map, use .get(keyName) method.
		for(String name: nameAge.keySet()){
			System.out.println("name: " + name + ", age: " + nameAge.get(name));
		}
	}
}


