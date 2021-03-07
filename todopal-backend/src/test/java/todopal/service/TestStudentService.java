package todopal.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import todopal.dao.StudentRepository;
import todopal.model.Student;

@ExtendWith(MockitoExtension.class)
public class TestStudentService {

	@Mock
	private StudentRepository studentRepository;

	private final String SD_EMAIL = "denytestSD@email.com";
	private final String SD_NAME = "John";
	private final String SD_PASSWORD = "password";

	@InjectMocks
	private StudentService service;

	@BeforeEach
	/**
	 * Setting up the mocks
	 */
	public void setMockOutput() {

		// studentRepository.save();
		lenient().doAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
			}
			return null;
		}).when(studentRepository).save(any(Student.class));

		// studentRepository.findByStudentEmail(any())
		lenient().when(studentRepository.findStudentByEmail(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SD_EMAIL)) {
				return makeTestingStudent(SD_EMAIL);
			} else {
				return null;
			}
		});
	}

	@Test
	public void testCreateStudent() {
		Student student = service.createStudent(SD_NAME, "testing@email.com", SD_PASSWORD);

		assertNotNull(student);
		assertEquals(SD_NAME, student.getName());
		assertEquals("testing@email.com", student.getEmail());
		assertEquals(SD_PASSWORD, student.getPassword());
	}

	@Test
	public void testCreateStudentIllegalArgument1() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createStudent(SD_NAME, "testingbademail.com", SD_PASSWORD);
		});

		String expectedMessage = "Invalid email is used";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateStudentIllegalArgument2() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createStudent(SD_NAME, SD_EMAIL, SD_PASSWORD);
		});

		String expectedMessage = "Already registered";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateStudentIllegalArgument3() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createStudent("", "testing@email.com", SD_PASSWORD);
		});

		String expectedMessage = "Student cannot have an empty name";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateStudentIllegalArgument4() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createStudent(SD_NAME, "testing@email.com", "");
		});

		String expectedMessage = "Student cannot have an empty password";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateStudentIllegalArgument5() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createStudent(SD_NAME, "", SD_PASSWORD);
		});

		String expectedMessage = "Student cannot have an empty email";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

        @Test
	public void testStudentLogin() {
		final Student student = makeTestingStudent(SD_EMAIL);
		assertEquals(student, service.logInStudent(SD_EMAIL, SD_PASSWORD));
	}

	private Student makeTestingStudent(String email) {
		Student student = new Student();
		student.setEmail(email);
		student.setName(SD_NAME);
		student.setPassword(SD_PASSWORD);

		return student;
	}
}
