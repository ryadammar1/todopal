package todopal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Classroom;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestClassroomPersistence {

	private final String CLASS_NAME = "NAME_TEST";

	@Autowired
	private ClassroomRepository classroomRepository;

	@AfterEach
	public void clearDatabase() {
		classroomRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadClassroom() {
		Classroom classroom = new Classroom();

		classroom.setClassroomId(1);
		classroom.setName(CLASS_NAME);

		classroomRepository.save(classroom);

		classroom = classroomRepository.findByClassroomId(1);
		assertNotNull(classroom);
		assertEquals(CLASS_NAME, classroom.getName());
	}

}