package com.epam.builderexample.datamanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.builderexample.db.ConnectionPool;
import com.epam.builderexample.serialization.PersonSerializer;
import com.epam.builderexample.model.Person;

/**
 * Class is responsible for reading and writing Person to DB
 * @author Pavel
 *
 */
public class DBDataManager extends DataManager {

	private static final int POOL_SIZE = 3;
	private static final String SQL_SELECT_ALL_PERSONS = "SELECT * FROM DESERIALIZED_PERSON";
	private static final String SQL_SELECT_PERSONS_BY_NAME = "SELECT * FROM DESERIALIZED_PERSON WHERE NAME=?";
	private static final String SQL_INSERT_DESERIALIZED_PERSON = "INSERT INTO DESERIALIZED_PERSON(PERSON_VALUE,NAME) VALUES(?,?)";
	private final static String WRITE_PERSON_MSG = "Person has just been written to DB.";
	private ConnectionPool connector;

	public DBDataManager() {
		try {
			connector = new ConnectionPool(POOL_SIZE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writePerson(Person person) {
		try {
			// 1.Getting connection
			Connection connection = connector.getConnection();

			// 2.Serializing of Person
			final String serializedPerson = PersonSerializer
					.serializePerson(person);

			// 3.Writing to DB
			final PreparedStatement ps = connection
					.prepareStatement(SQL_INSERT_DESERIALIZED_PERSON);
			ps.setString(1, serializedPerson);
			ps.setString(2, person.getName());
			ps.executeUpdate();
			System.out.println(WRITE_PERSON_MSG);
			ps.close();
			connector.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> readPerson() {
		List<Person> persons = new ArrayList<Person>();
		try {
			// 1.Getting connection
			Connection connection = connector.getConnection();

			// 2.Reading of serialized Persons
			PreparedStatement ps = connection
					.prepareStatement(SQL_SELECT_ALL_PERSONS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(3);
				String serializedPerson = rs.getString(2);

				// 3. Deserializing of each Person
				Person person = PersonSerializer
						.deserializePerson(serializedPerson);
				persons.add(person);
			}
			ps.close();
			connector.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public List<Person> readPerson(String name) {
		List<Person> persons = new ArrayList<Person>();
		try {
			// 1.Getting connection
			Connection connection = connector.getConnection();

			// 2.Reading of serialized Persons
			PreparedStatement ps = connection
					.prepareStatement(SQL_SELECT_PERSONS_BY_NAME);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String serializedPerson = rs.getString(2);

				// 3. Deserializing of each Person
				Person person = PersonSerializer
						.deserializePerson(serializedPerson);
				persons.add(person);
			}
			ps.close();
			connector.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return persons;
	}
}
