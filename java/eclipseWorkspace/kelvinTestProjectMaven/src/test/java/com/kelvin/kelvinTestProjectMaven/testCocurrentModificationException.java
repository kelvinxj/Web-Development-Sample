package com.kelvin.kelvinTestProjectMaven;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class testCocurrentModificationException {

	@Test
	public void test() {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		for(Integer i: list1){
			list1.remove(i);
			//break;
		}
	}

}
