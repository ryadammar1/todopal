package todopal.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    private final String SECONDARY_TEACHER_EMAIL = "test2teacher@pass.com";
    private final String WRONG_TEACHER_EMAIL = "bad";
    private final String EXISTING_LIST_TEACHER_EMAIL = "existing@list.com";
    private final String TEACHER_PASSWORD = "testTeacherpassword";
    private final String TEACHER_BIO = "testTeacherBio";
    private final String MANDATORY_LIST = "mandatorylist";
    private final String OPTIONAL_LIST = "optionallist";
    private final String PASSWORK = "password";

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
            } else if (((String) invocation.getArgument(0)).equals(WRONG_TEACHER_EMAIL) || ((String) invocation.getArgument(0)).equals(SECONDARY_TEACHER_EMAIL)) {
                return null;
            } else if (((String) invocation.getArgument(0)).equals(EXISTING_LIST_TEACHER_EMAIL)) {
			    return makeExistingListTestingTeacher();
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
        Teacher teacher = teacherService.createTeacher(APPROVAL_CODE, TEACHER_NAME, SECONDARY_TEACHER_EMAIL, TEACHER_PASSWORD, TEACHER_BIO);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals(TEACHER_NAME, teacher.getName());
        assertEquals(SECONDARY_TEACHER_EMAIL, teacher.getEmail());
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
        Teacher teacher = teacherService.updateTeacher(TEACHER_EMAIL, APPROVAL_CODE, "New Teacher", TEACHER_PASSWORD, TEACHER_BIO);

        assertNotNull(teacher);
        assertEquals(APPROVAL_CODE, teacher.getApprovalCode());
        assertEquals("New Teacher", teacher.getName());
        assertEquals(TEACHER_EMAIL, teacher.getEmail());
        assertEquals(TEACHER_PASSWORD, teacher.getPassword());
        assertEquals(TEACHER_BIO, teacher.getBio());
    }

    @Test 
    public void WithTeacherService_GivenExistTeacher_WhenDeletingTeacher_ThenTeacherIsDeleted() {
        boolean deleted = teacherService.deleteTeacher(TEACHER_EMAIL);

        assertEquals(true, deleted);
    }

    @Test
    public void testCreateMandatoryListSuccessful() {
        Teacher teacher = teacherService.addToMandatoryLists(TEACHER_EMAIL, MANDATORY_LIST);

        assertEquals(MANDATORY_LIST, teacher.getMandatoryLists().get(0));
    }

    @Test
    public void testCreateMandatoryListExistingName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.addToMandatoryLists(EXISTING_LIST_TEACHER_EMAIL, MANDATORY_LIST);
        });

        String expectedMessage = "Task category already exists";
        String actualMessage = exception.getMessage();

        assertEquals(true, actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateMandatoryListNoName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.addToMandatoryLists(TEACHER_EMAIL, "");
        });

        String expectedMessage = "Category name is not provided";
        String actualMessage = exception.getMessage();

        assertEquals(true, actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateOptionalListSuccessful() {
        Teacher teacher = teacherService.addToOptionalLists(TEACHER_EMAIL, OPTIONAL_LIST);

        assertEquals(OPTIONAL_LIST, teacher.getOptionalLists().get(0));
    }

    @Test
    public void testCreateOptionalListExistingName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.addToOptionalLists(EXISTING_LIST_TEACHER_EMAIL, OPTIONAL_LIST);
        });

        String expectedMessage = "Task category already exists";
        String actualMessage = exception.getMessage();

        assertEquals(true, actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateOptionalListNoName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.addToOptionalLists(TEACHER_EMAIL, "");
        });

        String expectedMessage = "Category name is not provided";
        String actualMessage = exception.getMessage();

        assertEquals(true, actualMessage.contains(expectedMessage));
    }

    @Test
    public void testLoginTeacher()
    {
        Teacher teacher = makeLoggedInTeacher(TEACHER_EMAIL);
        assertEquals(teacher.getName(), teacherService.logInTeacher(TEACHER_EMAIL, TEACHER_PASSWORD).getName());
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

    private Teacher makeExistingListTestingTeacher() {
        Teacher teacher = new Teacher();
        ArrayList<String> mandatoryLists = new ArrayList<>();
        ArrayList<String> optionalLists = new ArrayList<>();
        mandatoryLists.add(MANDATORY_LIST);
        optionalLists.add(OPTIONAL_LIST);
        teacher.setApprovalCode(APPROVAL_CODE);
        teacher.setName(TEACHER_NAME);
        teacher.setEmail(EXISTING_LIST_TEACHER_EMAIL);
        teacher.setPassword(TEACHER_PASSWORD);
        teacher.setBio(TEACHER_BIO);
        teacher.setMandatoryLists(mandatoryLists);
        teacher.setOptionalLists(optionalLists);
        return teacher;
    }

    private Teacher makeLoggedInTeacher(String email)
    {
        Teacher teacher = new Teacher();
        teacher.setEmail(email);
        teacher.setName(TEACHER_NAME);
        teacher.setPassword(PASSWORK);
        return teacher;
    }
}