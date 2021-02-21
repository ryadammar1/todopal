package todopal.service;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import todopal.dao.TaskContainerRepository;
import todopal.dao.TaskRepository;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;

@ExtendWith(MockitoExtension.class)
public class TestTaskService {

	@Mock
	private TaskRepository taskRepository;
	@Mock
	private TaskContainerRepository taskContainerRepository;

	private final boolean TASK_MANDATORY = true;
	private final String TASK_TAG = "MATH";
	private final String TASK_CATEGORY = "HOMEWORK";
	private final int TASK_POINTS = 5;
	private final String TASK_NAME = "MATH PROBLEM";
	private final String TASK_DESCRIPTION = "DO PROBLEM 1.1";
	private final long TASK_ID = 1;
	private final long TASK_CONTAINER_ID = 2;
	private final long TEST_DENY = 3;

	@InjectMocks
	private TaskService service;

	@BeforeEach
	/**
	 * Setting up the mocks 
	 */
	public void setMockOutput() {

		// taskRepository.save();
		lenient().doAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
			}
			return null;
		}).when(taskRepository).save(any(Task.class));

		// taskContainerRepository.save();
		lenient().doAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(null)) {
				throw new IllegalArgumentException();
			}
			return null;
		}).when(taskContainerRepository).save(any(TaskContainer.class));

		//taskRepository.findBytaskId(anyInt())
		lenient().when(taskRepository.findBytaskId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if ((Long) invocation.getArgument(0) == (TASK_ID)) {	 
				Task task = makeTestingTask();
				return task;
			} else {
				return null;
			}
		});
		
		//taskRepository.findBytaskId(anyLong())
				lenient().when(taskContainerRepository.findBytaskContainerId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
					if ((Long) invocation.getArgument(0) == (TASK_CONTAINER_ID)) {	 
						Task task = makeTestingTask();
						LocalDate realCompletionDate = LocalDate.parse("2021-02-13");
						TaskContainer container = new TaskContainer();
						container.setTaskContainerId(TASK_CONTAINER_ID);
						container.setStatus(TaskStatus.TODO);
						container.setCompletionDate(realCompletionDate);
						container.setTask(task);
						return container;
					} else {
						return null;
					}
				});
	}


	@Test 
	public void testCreateTask() {

		LocalDate realStartDate = LocalDate.parse("2021-02-13");		
		LocalDate realDueDate = LocalDate.parse("2021-02-16");

		Task task = service.createTask(2, "Problem 1.1", "Complete the problem", "Math", "Homework", true, 5, realStartDate, realDueDate);

		assertNotNull(task);
		assertEquals(2, task.getTaskId());
		assertEquals(true, task.isIsMandatory());
		assertEquals("Math", task.getTag());
		assertEquals("Homework", task.getCategory()); 
		assertEquals(5, task.getPointCount());
		assertEquals("Problem 1.1", task.getName());
		assertEquals("Complete the problem", task.getDescription());
		assertEquals(realStartDate, task.getStartDate());
		assertEquals(realDueDate, task.getDueDate());

	}

	@Test 
	public void testCreateTaskContainer() throws Exception {
		LocalDate realCompletionDate = LocalDate.parse("2021-02-13");

		TaskContainer taskContainer = service.createTaskContainer(3, realCompletionDate, TaskStatus.TODO, TASK_ID);	

		assertNotNull(taskContainer);
		assertEquals(3, taskContainer.getTaskContainerId());
		assertEquals(TaskStatus.TODO, taskContainer.getStatus());
		assertEquals(realCompletionDate, taskContainer.getCompletionDate());
		assertNotNull(taskContainer.getTask());
	}
	
	
	@Test
	public void testCreateTaskContainerIllegalArgument1() {
		LocalDate realCompletionDate = LocalDate.parse("2021-02-13");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTaskContainer(TASK_CONTAINER_ID, realCompletionDate, TaskStatus.TODO, TASK_ID);
		});

		String expectedMessage = "The task container was already created";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	
	@Test
	public void testCreateTaskContainerIllegalArgument2() {
		LocalDate realCompletionDate = LocalDate.parse("2021-02-13");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTaskContainer(3, realCompletionDate, TaskStatus.TODO, 5);
		});

		String expectedMessage = "Invalid Task Id";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	
	@Test
	public void testCreateTaskContainerIllegalArgument3() {
		LocalDate realCompletionDate = LocalDate.parse("2021-02-13");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTaskContainer(3, realCompletionDate, null, TASK_ID);
		});

		String expectedMessage = "Task container cannot have null status";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	
	@Test
	public void testCreateTaskIllegalArgument1() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTask(TASK_ID, "Problem 1.1", "Complete the problem", "Math", "Homework", true, 5, "2021-02-13", "2021-02-16");
		});

		String expectedMessage = "The task was already created";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testCreateTaskIllegalArgument2() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTask(2, "", "Complete the problem", "Math", "Homework", true, 5, "2021-02-13", "2021-02-16");
		});

		String expectedMessage = "Tasks cannot have an empty name";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testCreateTaskIllegalArgument3() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTask(2, "Problem 1.1", "Complete the problem", "Math", "Homework", true, -5, "2021-02-13", "2021-02-16");
		});

		String expectedMessage = "Tasks cannot have negative point values";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testCreateTaskIllegalArgument4() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			service.createTask(2, "Problem 1.1", "Complete the problem", "Math", "Homework", true, 5, "2021-02-13", "2021-02-10");
		});

		String expectedMessage = "Tasks cannot have due date before the starting date";
		String actualMessage = exception.getMessage();

		assertEquals(true, actualMessage.contains(expectedMessage));
	}

	@Test
	public void testDenyTaskStatus() {
		
	}
	
	//Helpers
	private TaskContainer makeTestingTaskContainer() {
		TaskContainer taskContainer = new TaskContainer();
		taskContainer.setTaskContainerId(TEST_DENY);
		taskContainer.setStatus(TaskStatus.DONE);
		taskContainer.setCompletionDate(LocalDate.parse("2021-02-13"));
		
		return taskContainer;
	}
	
	private Task makeTestingTask() {
		Task task = new Task();
		task.setTaskId(TASK_ID);
		task.setIsMandatory(TASK_MANDATORY);
		task.setTag(TASK_TAG);
		task.setCategory(TASK_CATEGORY);
		task.setPointCount(TASK_POINTS);
		task.setName(TASK_NAME);
		task.setDescription(TASK_DESCRIPTION);
		return task;
	}
}
