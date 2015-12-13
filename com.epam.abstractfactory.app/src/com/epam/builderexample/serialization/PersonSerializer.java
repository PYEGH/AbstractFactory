package com.epam.builderexample.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.builderexample.model.Person;

/**
 * Class is used for serialization and deserialization of Person Object.
 * 
 * @author Pavel
 * 
 */
public class PersonSerializer {

	/**
	 * Convert Person into string which contains array of bytes
	 * 
	 * @param person
	 * @return
	 */
	public static String serializePerson(Person person) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(person);
			oos.flush();
			baos.flush();
			oos.close();
			baos.close();
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte array[] = baos.toByteArray();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			result.append(' ');
		}
		return result.toString();
	}

	/**
	 * Converts serialized Person into Person Object.
	 * 
	 * @param serializedPerson
	 * @return
	 */
	public static Person deserializePerson(String serializedPerson) {
		Person person = null;
		byte[] byteArray = convertStringIntoByteArray(serializedPerson);

		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(bais);
			person = (Person) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;
	}

	/**
	 * Converts string into byte array. Format of string: -84 -19 0 5 115 114 0
	 * 36 ... . Delimiter for a sting is space symbol.
	 * 
	 * @param serializedPerson
	 * @return
	 */
	private static byte[] convertStringIntoByteArray(String serializedPerson) {
		Scanner scanner = new Scanner(serializedPerson);
		List<Byte> elements = new ArrayList<Byte>();
		while (scanner.hasNext()) {
			elements.add(Byte.parseByte(scanner.next()));

		}
		scanner.close();
		byte[] byteArray = new byte[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			byteArray[i] = elements.get(i);
		}
		return byteArray;
	}
}
