package todopal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Teacher;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTeacherPersistence {

	private final String TEACHER_NAME = "NAME_TEST";
	private final String TEACHER_EMAIL = "name.test@yahoo.ca";
    private final String TEACHER_BIO = "I love pen island!";
	private final String TEACHER_APPROVAL_CODE = "911";

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ClassroomRepository classroomRepository;

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

	private Teacher createTeacher() {
		final Teacher teacher = new Teacher();

		teacher.setEmail(TEACHER_EMAIL);
		teacher.setName(TEACHER_NAME);
		teacher.setBio(TEACHER_BIO);
		teacher.setApprovalCode(TEACHER_APPROVAL_CODE);
		
		return teacherRepository.save(teacher);
	}

	@Test
	public void testPersistAfterSave() {
		createTeacher();

		final Teacher savedTeacher = teacherRepository.findByEmail(TEACHER_EMAIL);

		assertNotNull(savedTeacher);
		assertEquals(TEACHER_NAME, savedTeacher.getName());
		assertEquals(TEACHER_BIO, savedTeacher.getBio());
		assertEquals(TEACHER_APPROVAL_CODE, savedTeacher.getApprovalCode());
	}

	@Test
    public void testUpdateTeacher() {
		final Teacher savedTeacher = createTeacher();

		final ArrayList<String> TASK_CATEGORIES = new ArrayList<String>();
		final String CLASS_NAME = "NAME_TEST";
		final long CLASS_ID = 1;

		final Classroom classroom = new Classroom();
		classroom.setClassroomId(CLASS_ID);
		classroom.setName(CLASS_NAME);
		classroom.setTaskCategories(TASK_CATEGORIES);
		classroom.setTeacher(savedTeacher);
		final Classroom savedClassroom = classroomRepository.save(classroom);

		final HashSet<Classroom> classrooms = new HashSet<>();
		classrooms.add(savedClassroom);

		final String TEACHER_NAME_2 = "OTHER_NAME_TEST";
		final String TEACHER_BIO_2 = "I love pen island 2!";
		final String TEACHER_APPROVAL_CODE_2 = "811";
		
		savedTeacher.setName(TEACHER_NAME_2);
		savedTeacher.setBio(TEACHER_BIO_2);
		savedTeacher.setApprovalCode(TEACHER_APPROVAL_CODE_2);
		savedTeacher.setClassroom(classrooms);
		
		final Teacher savedTeacherWithClassroom = teacherRepository.save(savedTeacher);
		
		Set<Classroom> classroomsWithTeacher  = savedTeacherWithClassroom.getClassroom();
		final Classroom classroomWithTeacher = (Classroom)classroomsWithTeacher.toArray()[0];

		assertNotNull(savedTeacherWithClassroom);
		assertEquals(TEACHER_NAME_2, savedTeacherWithClassroom.getName());
		assertEquals(TEACHER_BIO_2, savedTeacherWithClassroom.getBio());
		assertEquals(TEACHER_APPROVAL_CODE_2, savedTeacherWithClassroom.getApprovalCode());
		
		assertEquals(savedClassroom.getClassroomId(), classroomWithTeacher.getClassroomId());
		assertEquals(classroomWithTeacher.getTeacher().getEmail(), savedTeacherWithClassroom.getEmail());
    }
}
