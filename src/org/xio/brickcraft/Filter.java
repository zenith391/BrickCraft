package org.xio.brickcraft;

import java.util.ArrayList;

public class Filter {

	private ArrayList<Class<?>> klasses = new ArrayList<>();
	private ArrayList<Class<?>> interfaces = new ArrayList<>();
	
	public Class<?>[] getAcceptedClasses() {
		return klasses.toArray(new Class<?>[klasses.size()]);
	}
	
	public Class<?>[] getAcceptedInterfaces() {
		return interfaces.toArray(new Class<?>[interfaces.size()]);
	}
	
	
	/**
	 * Accept this class and all of it's childs (classes which the superclass is this one)
	 * @param klass
	 * @return
	 */
	public Filter acceptClass(Class<?> klass) {
		klasses.add(klass);
		return this;
	}
	
	public Filter acceptInterface(Class<?> i) {
		interfaces.add(i);
		return this;
	}

}
