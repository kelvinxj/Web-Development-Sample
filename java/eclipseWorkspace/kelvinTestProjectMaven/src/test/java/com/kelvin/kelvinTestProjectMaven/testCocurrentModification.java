package com.kelvin.kelvinTestProjectMaven;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
public class testCocurrentModification {
	
	@Test
	public void testTriggerCocurrentModification(){

		List<Integer> intList = new LinkedList<Integer>();
		
		intList.add(1);
		intList.add(2);
		intList.add(3);
		
		for(Integer i: intList){
			if(i.equals(2))
				intList.remove(i);
		}
		
		for(Integer k: intList){
			System.out.println(k);
		}
	}
	
	@Test
	public void testWillNOTTriggerCocurrentModification(){
		List<Integer> intList = new LinkedList<Integer>();
		
		intList.add(1);
		intList.add(2);
		intList.add(3);
		
		Iterator<Integer> i = intList.iterator();
		System.out.println("Before removing element:");
		while(i.hasNext()){
			Integer j = i.next();
			System.out.println(j);
			if(j.equals(2))
				i.remove();
		}

		System.out.println("After removing element:");
		for(Integer k: intList){
			System.out.println(k);
		}
	}
}
