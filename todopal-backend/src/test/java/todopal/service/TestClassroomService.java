package todopal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import todopal.dao.ClassroomRepository;
import todopal.model.Classroom;
import todopal.model.Teacher;

@ExtendWith(MockitoExtension.class)
public class TestClassroomService {

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
			return createClassroomMock();
		}).when(classroomRepository).findByClassroomId(any(Long.class));
	}
	
	private Classroom createClassroomMock() {
		Classroom classroomMock = new Classroom();

		classroomMock.setClassroomId(1);
		classroomMock.setTeacher(null);
		classroomMock.setName("group1");
		classroomMock.setImagePath("www.image.com");
		classroomMock.setSubject("math");

		return classroomMock;
	}
	
	@Test
	public void testCreateClassroomSucessfull() {
		Classroom classroom = service.createClassroom(null, "group1", "www.image.com", "math");

		assertEquals(classroom.getName(), "group1");
		assertEquals(classroom.getImagePath(), "www.image.com");
		assertEquals(classroom.getSubject(), "math");
		
		assertNotNull(classroom.getClassroomId());
	}
	
	@Test
	public void testCreateClassroomIllegalArgument() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createClassroom(null, "", "", "");
	    });
		
	    String expectedMessage = "String argument is empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testSetClassroomField() {
		Classroom classroom = service.setClassroomName(1, "group2");

		assertEquals(classroom.getName(), "group2");
		assertEquals(classroom.getImagePath(), "www.image.com");
		assertEquals(classroom.getSubject(), "math");
		
		assertNotNull(classroom.getClassroomId());
	}
	
	@Test
	public void testSetClassroomFieldIllegalArgument() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.setClassroomName(1, "");
	    });
		
	    String expectedMessage = "String argument is empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
}