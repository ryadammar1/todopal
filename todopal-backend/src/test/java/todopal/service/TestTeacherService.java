package todopal.service;

import static org.hamcrest.CoreMatchers.any;
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

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
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
    private final long CLASS_ID = 1;
    private final String CLASS_NAME = "testClassrrom";
    
    private final HashSet<Classroom> CLASSROOMS = new HashSet<Classroom>();

    private ArrayList<String> TASK_CATEGORIES = new ArrayList<String>();

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
            } else if ((int) invocation.getArgument(0) == WRONG_TEACHER_EMAIL) {
                return null;
            }
            return makeTestingTeacher();
		});

        // teacherRepository.update();
		lenient().doAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
            }
			return null;
		}).when(teacherRepository).update(any(Teacher.class));

        // teacherRepository.delete();
		lenient().doAnswer((InvocationOnMock invocation) -> {
            // do nothing
			return null;
		}).when(teacherRepository).delete((Teacher) any(Teacher.class));
    }

    @Test 
    public void WithTeacherService_GivenNoTeacher_WhenCreatingTeacher_ThenTeacherIsSaved() {
        Teacher teacher = teacherService.createTeacher(APPROVAL_CODE, TEACHER_NAME, TEACHER_EMAIL, TEACHER_PASSWORD, TEACHER_BIO, CLASSROOMS);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals(TEACHER_NAME, teacher.getName());
        assertEquals(TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
        assertNotNull(teacher.getClassroom());
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
        assertNotNull(teacher.getClassroom());
    }

    @Test 
    public void WithTeacherService_GivenNoTeacher_WhenFindingTeacher_ThenTeacherIsNotFound() {
        Teacher teacher = teacherService.getTeacher(WRONG_TEACHER_EMAIL);

        assertNull(teacher);
    }

    @Test 
    public void WithTeacherService_GivenExistTeacher_WhenUpdatingTeacher_ThenTeacherIsUpdated() {
        Teacher teacher = teacherService.updateTeacher(APPROVAL_CODE, TEACHER_NAME, TEACHER_EMAIL, TEACHER_PASSWORD, TEACHER_BIO, CLASSROOMS);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals(TEACHER_NAME, teacher.getName());
        assertEquals(TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
        assertNotNull(teacher.getClassroom());
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
        CLASSROOMS.add(getTestClassroom());
        teacher.setClassroom(CLASSROOMS);
        return teacher;
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