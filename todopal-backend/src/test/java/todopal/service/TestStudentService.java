package todopal.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import todopal.dao.StudentRepository;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Task;
import todopal.model.TaskContainer;

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
		assertEquals(student.getName(), service.logInStudent(SD_EMAIL, SD_PASSWORD).getName());
	}
        
    @Test
       public void testGetStudentName() {
    	String name = service.getStudentName(SD_EMAIL);
        assertEquals(name, SD_NAME);
        
       }     
        
    @Test
    public void testGetStudentPersonalTasks() {
    	Set<TaskContainer> personalTasks = service.getStudentPersonalTasks(SD_EMAIL);

    	ArrayList<String> personalTasksName = new ArrayList<String>();
    	
    	int i = 0;
    	for(TaskContainer tc : personalTasks) {
    		personalTasksName.add(tc.getTask().getName());
		}
    	
    	for(int j = 0; j < 3; j++)
    		assertTrue(personalTasksName.contains(this.personalTasks[j]));
    }

    @Test
    public void testGetStudentSchoolTasks() {
    	Set<TaskContainer> schoolTasks = service.getStudentSchoolTasks(SD_EMAIL);
    	
    	ArrayList<String> schoolTasksName = new ArrayList<String>();
    	
    	int i = 0;
    	for(TaskContainer tc : schoolTasks) {
    		schoolTasksName.add(tc.getTask().getName());
		}
    	
    	for(int j = 0; j < 3; j++)
    		assertTrue(schoolTasksName.contains(this.schoolTasks[j]));
    	
    }

    String[] schoolTasks = {"st1", "st2", "st3"};
    String[] personalTasks = {"pt1", "pt2", "pt3"};

	@Test
	public void testGetStudent() {
		Student student = service.getStudent(SD_EMAIL);

		assertNotNull(student);
		assertEquals(SD_NAME, student.getName());
		assertEquals(SD_EMAIL, student.getEmail());
		assertEquals(SD_PASSWORD, student.getPassword());
	}

	@Test
	public void testGetStudentIllegalArgument1() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.getStudent("notarealstudent@email.com");
		});

		String expectedMessage = "Non-existant Student";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testGetStudentIllegalArgument2() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.getStudent("");
		});

		String expectedMessage = "Student email cannot be empty!";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	private Student makeTestingStudent(String email) {
		Student student = new Student();
		student.setEmail(email);
		student.setName(SD_NAME);
		student.setPassword(SD_PASSWORD);
		student.setClassroom(createClassroomMockFill());
		
		HashSet<TaskContainer> schoolTasks = new HashSet<TaskContainer>();
		HashSet<TaskContainer> personalTasks = new HashSet<TaskContainer>();
		
		for(int i = 0; i < 3; i++) {
			schoolTasks.add(makeTaskContainer(this.schoolTasks[i]));
			personalTasks.add(makeTaskContainer(this.personalTasks[i]));
		}
		
		student.setSchoolTask(schoolTasks);
		student.setPersonalTask(personalTasks);

		return student;
	}
	
	private TaskContainer makeTaskContainer(String taskName) {
		Task task = new Task();
		task.setName(taskName);
		TaskContainer taskcontainer = new TaskContainer();
		taskcontainer.setTask(task);
		
		return taskcontainer;
	}

	private Classroom createClassroomMockFill() {
		Classroom classroomMock = new Classroom();

		classroomMock.setClassroomId(1);
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

}
