package todopal.service;

import static org.mockito.ArgumentMatchers.any; 
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.lenient;

import java.util.*;

import org.hibernate.mapping.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import jdk.jfr.Timestamp;
import todopal.dao.TeacherRepository;
import todopal.model.Classroom;
import todopal.model.Teacher;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class TestTeacherService {
    // mock stubs
    @Mock
	private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    // teacher's values
    private final String APPROVAL_CODE = "appr123";
    private final String TEACHER_NAME = "testTeacher";
    private final String TEACHER_EMAIL = "testTeacher@email.com";
    private final String WRONG_TEACHER_EMAIL = "bad";
    private final String TEACHER_PASSWORD = "testTeacherpassword";
    private final String TEACHER_BIO = "testTeacherBio";

    // tests
    @BeforeEach
	public void setMockOutput() {
        // teacherRepository.save();
		lenient().doAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
            }
			return null;
		}).when(teacherRepository).save((Teacher) any(Teacher.class));

        // teacherRepository.find();
		lenient().when(teacherRepository.findTeacherByEmail(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
            } else if ((String) invocation.getArgument(0) == WRONG_TEACHER_EMAIL) {
                return null;
            }
            return makeTestingTeacher();
		});

        // teacherRepository.delete();
		lenient().doAnswer((InvocationOnMock invocation) -> {
            // do nothing
			return null;
		}).when(teacherRepository).delete((Teacher) any(Teacher.class));
    }

    @Test 
    public void WithTeacherService_GivenNoTeacher_WhenCreatingTeacher_ThenTeacherIsSaved() {
        Teacher teacher = teacherService.createTeacher(APPROVAL_CODE, TEACHER_NAME, WRONG_TEACHER_EMAIL, TEACHER_PASSWORD, TEACHER_BIO);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals(TEACHER_NAME, teacher.getName());
        assertEquals(WRONG_TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
    }

    @Test 
    public void WithTeacherService_GivenSavedTeacher_WhenFindingTeacher_ThenTeacherIsFound() {
        Teacher teacher = teacherService.getTeacher(TEACHER_EMAIL);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals(TEACHER_NAME, teacher.getName());
        assertEquals(TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
    }

    @Test 
    public void WithTeacherService_GivenNoTeacher_WhenFindingTeacher_ThenTeacherIsNotFound() {
        Teacher teacher = teacherService.getTeacher(WRONG_TEACHER_EMAIL);

        assertNull(teacher);
    }

    @Test 
    public void WithTeacherService_GivenExistTeacher_WhenUpdatingTeacher_ThenTeacherIsUpdated() {
        Teacher teacher = teacherService.createTeacher(APPROVAL_CODE, TEACHER_NAME, WRONG_TEACHER_EMAIL, TEACHER_PASSWORD, TEACHER_BIO);
        teacher = teacherService.updateTeacher(WRONG_TEACHER_EMAIL, APPROVAL_CODE, "New Teacher", TEACHER_PASSWORD, TEACHER_BIO);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals("New Teacher", teacher.getName());
        assertEquals(WRONG_TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
    }

    @Test 
    public void WithTeacherService_GivenExistTeacher_WhenDeletingTeacher_ThenTeacherIsDeleted() {
        boolean deleted = teacherService.deleteTeacher(TEACHER_EMAIL);

        assertEquals(true, deleted);
    }


    // helpers
    private Teacher makeTestingTeacher() {
        Teacher teacher = new Teacher();
        teacher.setApprovalCode(APPROVAL_CODE);
        teacher.setName(TEACHER_NAME);
        teacher.setEmail(TEACHER_EMAIL);
        teacher.setPassword(TEACHER_PASSWORD);
        teacher.setBio(TEACHER_BIO);
        return teacher;
    }
}