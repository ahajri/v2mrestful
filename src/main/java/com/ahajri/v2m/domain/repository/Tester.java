package com.ahajri.v2m.domain.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import com.ahajri.v2m.domain.Person;

public class Tester {
	private static Person person =new Person();
	private static Random random = new Random();

	static{
		person.setId(random.nextInt(999));
	}
	public static void main(String[] args) {
		Method getIdMethod;
		try {
			getIdMethod = person.getClass().getDeclaredMethod("getId",
					null);
			Long id = (Long) getIdMethod.invoke(person, null);
			System.out.println("<ID Person = "+id+">");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
