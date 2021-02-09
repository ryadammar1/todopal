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

	@AfterEach
	public void clearDatabase() {
		teacherRepository.deleteAll();
	}

	@Test
	public void testPersistAfterSave() {
		Teacher teacher = new Teacher();

		teacher.setEmail(TEACHER_EMAIL);
		teacher.setName(TEACHER_NAME);
		teacher.setBio(TEACHER_BIO);
		teacher.setApprovalCode(TEACHER_APPROVAL_CODE);

		teacherRepository.save(teacher);
		Teacher savedTeacher = teacherRepository.findByEmail(TEACHER_EMAIL);

		assertNotNull(savedTeacher);
		assertEquals(TEACHER_NAME, savedTeacher.getName());
		assertEquals(TEACHER_BIO, savedTeacher.getBio());
		assertEquals(TEACHER_APPROVAL_CODE, savedTeacher.getApprovalCode());
	}

}
