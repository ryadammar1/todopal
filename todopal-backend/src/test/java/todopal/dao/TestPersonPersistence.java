package todopal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Person;
import todopal.model.Teacher;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersonPersistence {

	private final String PERSON_NAME = "NAME_TEST";
	private final String PERSON_EMAIL = "name.test@yahoo.ca";
    private final String PERSON_BIO = "I love pen island!";

	@Autowired
	private PersonRepository personRepository;

	@AfterEach
	public void clearDatabase() {
		personRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadClassroom() {
		Person person = new Teacher();

		person.setEmail(PERSON_EMAIL);
		person.setName(PERSON_NAME);
		person.setBio(PERSON_BIO);

		personRepository.save(person);
		Person savedPerson = personRepository.findByEmail(PERSON_EMAIL);

		assertNotNull(savedPerson);
		assertEquals(PERSON_NAME, person.getName());
		assertEquals(PERSON_BIO, person.getBio());
	}

}
