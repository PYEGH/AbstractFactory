package com.epam.builderexample.model;

import java.io.Serializable;

/**
 * Class is a model of real person.
 * @author Pavel
 *
 */
public class Person implements Serializable{

	private static final long serialVersionUID = -165233557423770878L;
	private int age;
	private String name;
	
	public Person( final String name,final int age){
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}	
}
