package com.epam.builderexample.datamanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.builderexample.model.Person;

/**
 * Class is responsible for reading and writing Person to File
 * 
 * @author Pavel
 * 
 */
public class FileDataManager extends DataManager {

	private final static String FILE_NAME = "C:\\TMP\\persons.txt";
	private final static String WRITE_PERSON_MSG = "Person has just been written to file.";
	private final static String READ_PERSONS_MSG = "Persons were sucsesfully readed.";
	private final static String FILE_NOT_EXISTS_MSG = "Selected file does not exist.";

	@Override
	public void writePerson(final Person person) {
		try {
			File file = new File(FILE_NAME);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// Wrapping with a buffer
			BufferedWriter bw = new BufferedWriter(fw);

			// Writing to the file
			bw.write(person.getName() + ' ' + person.getAge());

			bw.close();
			System.out.println(WRITE_PERSON_MSG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> readPerson() {
		List<Person> persons = new ArrayList<Person>();

		File file = new File(FILE_NAME);
		if (!file.exists()) {
			System.out.println(FILE_NOT_EXISTS_MSG);
			return null;
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					file.getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					final Person person = convertStringIntoPerson(s);
					persons.add(person);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(READ_PERSONS_MSG);
		return persons;
	}

	@Override
	public List<Person> readPerson(String name) {
		List<Person> persons = new ArrayList<Person>();

		File file = new File(FILE_NAME);
		if (!file.exists()) {
			System.out.println(FILE_NOT_EXISTS_MSG);
			return null;
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					file.getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					final Person person = convertStringIntoPerson(s);
					if (name.equals(person.getName())) {
						persons.add(person);
					}
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return persons;
	}

	/**
	 * Converts line from file into Person. File must contain only line with
	 * next format: Name Age Order of fields is necessary.
	 * 
	 * @param lineFromFile
	 * @return
	 */
	private Person convertStringIntoPerson(String lineFromFile) {
		Scanner scanner = new Scanner(lineFromFile);
		String personName = scanner.next();
		int personAge = Integer.parseInt(scanner.next());
		scanner.close();
		return new Person(personName, personAge);
	}
}
