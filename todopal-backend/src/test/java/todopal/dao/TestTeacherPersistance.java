package todopal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Teacher;
import todopal.model.Classroom;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTeacherPersistence {

    // teacher
    private final String APPROVAL_CODE = "appr123";

    // person
    private final String TEACHER_NAME = "testTeacher";
    private final String TEACHER_EMAIL = "testTeacher@email.com";
    private final String TEACHER_PASSWORD = "testTeacherpassword";
    private final String TEACHER_BIO = "testTeacherBio";
    private Set<Classroom> CLASSROOMS = new Set<Classroom>();

    // classroom
    private final String CLASS_NAME = "NAME_TEST";
	private final long CLASS_ID = 1;
	private ArrayList<String> TASK_CATEGORIES = new ArrayList<String>();

	@Autowired
	private TeacherRepository teacherRepository;

	@AfterEach
	public void clearDatabase() {
		teacherRepository.deleteAll();
	}

    @Test
    public void testPersistAndLoadTeacher() {
        Teacher teacher = new Teacher();
        teacher.setApprovalCode(APPROVAL_CODE);
        teacher.setName(TEACHER_NAME);
        teacher.setEmail(TEACHER_EMAIL);
        teacher.setPassword(TEACHER_PASSWORD);
        teacher.setBio(TEACHER_BIO);
        CLASSROOMS.add(getTestClassroom());
        teacher.setClassroom(CLASSROOMS());

        teacherRepository.save(teacher);

        teacher = teacherRepository.findByTeacherEmail(TEACHER_EMAIL);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals(TEACHER_NAME, teacher.getName());
        assertEquals(TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
        assertNotNull(teacher.getClassroom());
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setApprovalCode(APPROVAL_CODE);
        teacher.setName(TEACHER_NAME);
        teacher.setEmail(TEACHER_EMAIL);
        teacher.setPassword(TEACHER_PASSWORD);
        teacher.setBio(TEACHER_BIO);
        CLASSROOMS.add(getTestClassroom());
        teacher.setClassroom(CLASSROOMS());

        teacherRepository.save(teacher);

        teacher.setName("newTestTeacherName");

        teacherRepository.update(teacher);

        teacher = teacherRepository.findByTeacherEmail(TEACHER_EMAIL);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals("newTestTeacherName", teacher.getName());
        assertEquals(TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
        assertNotNull(teacher.getClassroom());
    }

    @Test
    public void testDeleteTeacher() {
        Teacher teacher = new Teacher();
        teacher.setApprovalCode(APPROVAL_CODE);
        teacher.setName(TEACHER_NAME);
        teacher.setEmail(TEACHER_EMAIL);
        teacher.setPassword(TEACHER_PASSWORD);
        teacher.setBio(TEACHER_BIO);
        CLASSROOMS.add(getTestClassroom());
        teacher.setClassroom(CLASSROOMS());

        teacherRepository.save(teacher);
        teacherRepository.deleteByTeacherEmail(TEACHER_EMAIL);

        teacher = teacherRepository.findByTeacherEmail(TEACHER_EMAIL);

        assertNull(teacher);
    }

    private Classroom getTestClassroom() {
        Classroom classroom = new Classroom();
		
		TASK_CATEGORIES.add("Math");
		TASK_CATEGORIES.add("English");
		TASK_CATEGORIES.add("Physics");

		classroom.setClassroomId(CLASS_ID);
		classroom.setName(CLASS_NAME);
		classroom.setTaskCategories(TASK_CATEGORIES);

        return classroom;
    }

}