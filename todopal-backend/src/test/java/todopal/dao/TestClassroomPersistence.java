package todopal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Classroom;
import todopal.model.Teacher;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestClassroomPersistence {

	private final String CLASS_NAME = "NAME_TEST";
	private final long CLASS_ID = 1;

	private ArrayList<String> TASK_CATEGORIES = new ArrayList<String>();

	@Autowired
	private ClassroomRepository classroomRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@AfterEach
	public void clearDatabase() {
		for (Teacher t : teacherRepository.findAll()) {
			for (Classroom c : t.getClassroom()) {
				c.setTeacher(null);
				classroomRepository.save(c);
			}
			t.setClassroom(new HashSet<Classroom>());
			teacherRepository.save(t);
		}
		teacherRepository.deleteAll();
		classroomRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadClassroom() {
		Classroom classroom = new Classroom();
		
		TASK_CATEGORIES.add("Math");
		TASK_CATEGORIES.add("English");
		TASK_CATEGORIES.add("Physics");

		classroom.setClassroomId(CLASS_ID);
		classroom.setName(CLASS_NAME);
		classroom.setTaskCategories(TASK_CATEGORIES);

		classroomRepository.save(classroom);
		classroom = classroomRepository.findByClassroomId(CLASS_ID);

		assertNotNull(classroom);
		assertEquals(CLASS_NAME, classroom.getName());
		assertEquals("Math", classroom.getTaskCategories().get(0));
		assertEquals("English", classroom.getTaskCategories().get(1));
		assertEquals("Physics", classroom.getTaskCategories().get(2));
	}

}