package com.kelvin.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;





import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

//BlockJUnit4ClassRunner is the default JUnit4 test runner. 
//it's not necessary to add @RunWith(BlockJUnit4ClassRunner.class)
//@RunWith(BlockJUnit4ClassRunner.class)
//@RunWith(MyTestRunner.class)
public class BasicTest {

	@Test
	public void testEquals() {
		System.out.println("Create new environment begins here!!");
		int i = 1;
		assertEquals(1, i);
	}
	
	@Test
	public void testMaxValueLong(){
		System.out.println(Long.MAX_VALUE);
		long maxPlusOne = 9223372036854775807L + 1;
		System.out.println(maxPlusOne);
	}
	
	@Test
	public void testNotEquals(){
		int i = 2;
		assertEquals(i,1);
	}
	
	@Test
	public void testTreeMap(){
		dateTimeCompare comp = new dateTimeCompare();
		TreeMap<String, Integer> relationships = new TreeMap<String, Integer>(comp);
		
		relationships.put("abc", 1);
		relationships.put("few", 34);
		relationships.put("123", 333);
		
		for(String key: relationships.keySet()){
			System.out.println(key + ":" + relationships.get(key));
		}
	}
	
	@Test
	public void testClassLoaderPath(){
		// Get classpath values
		String bootClassPath = System.getProperty("sun.boot.class.path");
		String extClassPath = System.getProperty("java.ext.dirs");
		String appClassPath = System.getProperty("java.class.path");
		// Print them out
		System.out.println("Bootstrap classpath =" + bootClassPath + "\n");
		System.out.println("Extensions classpath =" + extClassPath + "\n");
		System.out.println("Application classpath=" + appClassPath + "\n");
		
		System.out.println(this.getClass().getClassLoader());
	}
	
	public class dateTimeCompare implements Comparator<String>{

		@Override
		public int compare(String str1, String str2) {
			return str2.compareTo(str1);
		}
		
	}
	
	@Test
	public void nullInteration(){
		List<String> l1 = null;
		for(String str : l1){
			System.out.println(l1);
		}
	}
	
	@Test
	public void testBooleanObject(){
		List<Boolean> change = new ArrayList<Boolean>();
		change.add(true);
		changeValue(change);
		System.out.print(change.get(0));
	}

	private void changeValue(List<Boolean> result) {
		Boolean a = !result.get(0);
		result.remove(0);
		result.add(a);
	}
	
	@Test
	public void copyHashTable(){
		Hashtable ht1 = new Hashtable();
		Hashtable ht2 = new Hashtable();
		ht1.put("name", "Kelvin");
		ht1.put("Score", "max");
		
		ht2.putAll(ht1);
		
		for(Object key : ht2.keySet()){
			System.out.println(key + ":" + ht2.get(key));
		}
		
		//change value
		ht2.put("name","NewOne");
		System.out.println(ht1.get("name"));
		System.out.println(ht2.get("name"));
		
	}
	
	@Test
	public void setValueToArrayList(){
		List<Boolean> changed = new ArrayList<Boolean>();
		if(changed.size() == 0)
			changed.add(false);
		changed.set(0, true);
		System.out.println(changed.get(0));
	}
	
	@Test
	public void generateRandomWord(){
		System.out.println(getRandomWord(5));
		System.out.println(getRandomWord(7));
//		char ch = 'A';
//		for(int i = 0;i <=25; i++){
//			ch = (char)(65+i);
//			System.out.println(ch);
//		}
	}
	
	public String getRandomWord(int length){
		Random rd = new Random();
		int increment = 0;
		char[] cArray = new char[length];
		for(int i = 0; i < length; i++){
			increment = Math.abs(rd.nextInt()%25);
			if(rd.nextFloat() >= .5)
				cArray[i] = (char)(65+increment);
			else
				cArray[i] = (char)(97 + increment);
		}
		return new String(cArray);
	}
	
	public enum nameType{
		Latin,DBCS
	}
	
	@Test
	public void usingEnum(){
		nameType type1 = nameType.Latin;
		nameType type2 = nameType.DBCS;
		
		assertNotEquals(type1, type2);
		assertEquals(nameType.Latin,nameType.Latin);
		assertEquals(nameType.DBCS,nameType.DBCS);
	}
	
	@Test
	public void test2018(){
		int num = 0;
		for(int i = 0;i<2018;i++){
			if(i == 0){
				num = 130;
				System.out.println((i+1) + ":" + num);
			}
			else{
				if(num % 2 == 0){
					num = num/2 + 2;
				}
				else{
					num = num * 2 -2;
				}
				System.out.println((i+1) + ":" + num);
			}
		}
	}
}
