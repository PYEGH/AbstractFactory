package com.epam.builderexample.runner;

import java.util.List;

import com.epam.builderexample.datamanager.DataManager;
import com.epam.builderexample.factory.AbstractDataManagerFactory;
import com.epam.builderexample.factory.DataManagerFactory;
import com.epam.builderexample.model.Person;

public class DataManagingSimulator {
	public static void simulatePersonWritingAndReading() {
		AbstractDataManagerFactory factory = null;
		factory = new DataManagerFactory();
		final Person person = new Person("Ivan2", 202);
		
		// Simulation of writing/reading to File
		System.out.println("\nSimulation of writing/reading to File:");
		DataManager dataManager = factory.createFileDataManager();
		simulateWritingAndReading(dataManager, person);

		// Simulation of writing/reading to DB
		System.out.println("\nSimulation of writing/reading to DB");
		dataManager = factory.createDBDataManager();
		simulateWritingAndReading(dataManager, person);
		
		System.out.println("The end.");

	}

	private static void simulateWritingAndReading(
			final DataManager dataManager, final Person person) {
		//1.Simulation of writing
		dataManager.writePerson(person);
		
		//2.Simulation of reading
		List<Person> persons = dataManager.readPerson();
		System.out.println("\nAll persons:");
		showResult(persons);
		
		//3.Simulation of reading by name
		//3.1 There is no such person at store
		final String notExistingName = "asdfasdfasdfasdf";
		simulateReadingOfParticularPerson(dataManager,notExistingName);
		
		//3.2 Such person is exists at store
		final String existingName = person.getName();
		simulateReadingOfParticularPerson(dataManager,existingName);

	}
	
	private static void simulateReadingOfParticularPerson(final DataManager dataManager,String name){

		List<Person> personsWithParticularName = dataManager.readPerson(name);
		if(personsWithParticularName.isEmpty()){
			System.out.println("\nPersons by name" + ' ' + name + " were not found.");
		}else{
			System.out.println("\nPersons by name" + ' ' + name + ':');
			showResult(personsWithParticularName);
		}
	}
	
	private static void showResult(List<Person> persons){
		for(Person person: persons){
			System.out.println("Name:" + person.getName() + ' ' + "Age:" + person.getAge());
		}
	}

}
