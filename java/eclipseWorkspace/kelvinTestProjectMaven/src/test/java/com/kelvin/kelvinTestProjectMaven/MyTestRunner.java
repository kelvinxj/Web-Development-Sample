package com.kelvin.kelvinTestProjectMaven;

import java.util.LinkedList;
import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class MyTestRunner extends BlockJUnit4ClassRunner{

	public MyTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		List<FrameworkMethod> list = new LinkedList<FrameworkMethod>();
		try {
			list.add(new FrameworkMethod(BasicTest.class.getDeclaredMethod("testEquals", null)));
			list.add(new FrameworkMethod(BasicTest.class.getDeclaredMethod("testMaxValueLong", null)));
//			list.add(new FrameworkMethod(DateTimeTest.class.getDeclaredMethod("testDateTimeParsing", null)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
