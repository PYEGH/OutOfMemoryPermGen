package com.epam.oom.runner;

import javassist.CannotCompileException;

/*
 * Here we are using framework javassist. It generates classes at runtime.
 * On each iteration new class is generated. New generated classes require space at PermGen area.
 *  So this timeless cycle will lead us to OuyOfMemoryError PermGen space
 */
public class OOMRunner {
	private static javassist.ClassPool cp = javassist.ClassPool.getDefault();

	public static void main(String[] args) {

		for (int i = 0;; i++) {
			Class c;
			try {
				c = cp.makeClass("com.epam.oom.GeneratedClass" + i).toClass();
				System.out.println("Comapiled class " + c);
			} catch (CannotCompileException | RuntimeException
					| OutOfMemoryError e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
