package todopal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import todopal.dao.ClassroomRepository;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Teacher;

@ExtendWith(MockitoExtension.class)
public class TestClassroomService {

	private final long TEST_ID = 0;
	private final long TEST_ID_FILL = 1;
	private final long TEST_ID_EMPTY = 2;

	private final Classroom VOID_CLASSROOM = null;

	// teacher's values
	private final String APPROVAL_CODE = "appr123";
	private final String TEACHER_NAME = "testTeacher";
	private final String TEACHER_EMAIL = "testTeacher@email.com";
	private final String WRONG_TEACHER_EMAIL = "bad";
	private final String TEACHER_PASSWORD = "testTeacherpassword";
	private final String TEACHER_BIO = "testTeacherBio";
	private final long CLASS_ID = 1;
	private final String CLASS_NAME = "testClassrrom";

	@Mock
	private ClassroomRepository classroomRepository;

	@InjectMocks
	private ClassroomService service;

	@BeforeEach
	public void setMockOutput() {

		lenient().doAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
			}
			return null;
		}).when(classroomRepository).save(any(Classroom.class));

		lenient().doAnswer((InvocationOnMock invocation) -> {

			return null;
		}).when(classroomRepository).findByClassroomId(not(eq(TEST_ID)));

		lenient().doAnswer((InvocationOnMock invocation) -> {

			return createClassroomMock();
		}).when(classroomRepository).findByClassroomId(TEST_ID);

		lenient().doAnswer((InvocationOnMock invocation) -> {

			return createClassroomMockFill();
		}).when(classroomRepository).findByClassroomId(TEST_ID_FILL);

		lenient().doAnswer((InvocationOnMock invocation) -> {

			return createClassroomMockEmpty();
		}).when(classroomRepository).findByClassroomId(TEST_ID_EMPTY);
	}

	private Classroom createClassroomMock() {
		Classroom classroomMock = new Classroom();

		classroomMock.setClassroomId(TEST_ID);
		classroomMock.setTeacher(null);
		classroomMock.setName("group1");
		classroomMock.setImagePath("www.image.com");
		classroomMock.setSubject("math");

		return classroomMock;
	}

	private Classroom createClassroomMockFill() {
		Classroom classroomMock = new Classroom();

		classroomMock.setClassroomId(TEST_ID_FILL);
		classroomMock.setTeacher(null);
		classroomMock.setName("group2");
		classroomMock.setImagePath("www.image.com");
		classroomMock.setSubject("french");

		Set<Student> students = new HashSet<Student>();
		Student stu1 = new Student();
		stu1.setName("stu1");
		Student stu2 = new Student();
		stu2.setName("stu2");
		Student stu3 = new Student();
		stu3.setName("stu3");

		students.add(stu1);
		students.add(stu2);
		students.add(stu3);

		classroomMock.setStudent(students);

		return classroomMock;
	}

	private Classroom createClassroomMockEmpty() {
		Classroom classroomMock = new Classroom();

		classroomMock.setClassroomId(TEST_ID_EMPTY);
		classroomMock.setTeacher(null);
		classroomMock.setName("group2");
		classroomMock.setImagePath("www.image.com");
		classroomMock.setSubject("french");

		Set<Student> students = new HashSet<Student>();

		classroomMock.setStudent(students);

		return classroomMock;
	}

	@Test
	public void testGetAllStudentNamesSuccess() {
		List<String> students = service.getAllClassroomStudentsNames(createClassroomMockFill());
		for (int i = 1; i <= 3; i++) {
			students.get(i - 1).equals("stu" + i);
		}
	}
	
	@Test
	public void testGetAllStudentNamesEmpty() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.getAllClassroomStudentsNames(createClassroomMockEmpty());
		});
		
		String expectedMessage = "Class is empty!";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testGetAllStudentNamesNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.getAllClassroomStudentsNames(null);
		});
		
		String expectedMessage = "Inexistent Classroom";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateClassroomSucessfull() {
		Teacher teacher = makeTestingTeacher();

		Classroom classroom = service.createClassroom(teacher, "group1", "www.image.com", "math");

		assertEquals(classroom.getName(), "group1");
		assertEquals(classroom.getImagePath(), "www.image.com");
		assertEquals(classroom.getSubject(), "math");

		assertNotNull(classroom.getClassroomId());
	}

	@Test
	public void testCreateClassroomIllegalArgument1() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createClassroom(null, "", "image", "subject");
		});

		String expectedMessage = "String argument is empty";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateClassroomIllegalArgument2() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createClassroom(null, "group", "", "subject");
		});

		String expectedMessage = "String argument is empty";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateClassroomIllegalArgument3() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createClassroom(null, "group", "image", "");
		});

		String expectedMessage = "String argument is empty";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testSetClassroomField() {
		Classroom classroom = service.setClassroomName(TEST_ID, "group2");

		assertEquals(classroom.getName(), "group2");
		assertEquals(classroom.getImagePath(), "www.image.com");
		assertEquals(classroom.getSubject(), "math");

		assertNotNull(classroom.getClassroomId());
	}

	@Test
	public void testSetClassroomFieldIllegalArgument() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.setClassroomName(TEST_ID, "");
		});

		String expectedMessage = "String argument is empty";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
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