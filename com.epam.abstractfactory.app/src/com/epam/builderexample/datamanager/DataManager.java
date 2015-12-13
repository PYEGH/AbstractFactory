package com.epam.builderexample.datamanager;

import java.util.List;

import com.epam.builderexample.model.Person;

public abstract class DataManager {
	/**
	 * Writes Person.
	 * @param person
	 */
	public abstract void writePerson(final Person person);
	
	/**
	 * Reads all Persons.
	 * @return
	 */
	public abstract List<Person> readPerson();
	
	/**
	 * Reads all persons with particular name.
	 * @param name
	 * @return
	 */
	public abstract List<Person> readPerson(final String name);
}
