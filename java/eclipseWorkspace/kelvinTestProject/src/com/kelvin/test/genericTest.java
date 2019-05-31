package com.kelvin.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class genericTest {
	
	private class parent{
		
	}
	
	private class child extends parent{
		
	}
	
	private void doTest(List<? extends parent> list){
		System.out.println("List's total size: " + list.size());
	}

	@Test
	public void test() {
		List<child> childList = new LinkedList<child>();
		childList.add(new child());
		childList.add(new child());
		
		doTest(childList);
	}

}
