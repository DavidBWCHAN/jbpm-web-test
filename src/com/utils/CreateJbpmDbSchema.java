package com.utils;

import org.jbpm.JbpmConfiguration;

public class CreateJbpmDbSchema {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.createSchema();
	}

}
