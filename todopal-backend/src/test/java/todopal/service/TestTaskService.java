package todopal.service;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

	//Helpers
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
