package todopal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Task;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTaskPersistence {

	private final boolean TASK_MANDATORY = true;
	private final String TASK_TAG = "MATH";
	private final String TASK_CATEGORY = "HOMEWORK";
	private final int TASK_POINTS = 5;
	private final String TASK_NAME = "MATH PROBLEM";
	private final String TASK_DESCRIPTION = "DO PROBLEM 1.1";
	private final int TASK_ID = 1;
	
	
	@Autowired
	private TaskRepository taskRepository;

	@AfterEach
	public void clearDatabase() {
		taskRepository.deleteAll();
	}
	
	
	@Test
	public void testPersistAfterSave() {
		
		Task newTask = new Task();
		
		
		newTask.setIsMandatory(TASK_MANDATORY);
		newTask.setTag(TASK_TAG);
		newTask.setCategory(TASK_CATEGORY); 
		newTask.setPointCount(TASK_POINTS);
		newTask.setName(TASK_NAME);
		newTask.setDescription(TASK_DESCRIPTION);
		newTask.setTaskId(TASK_ID);

		taskRepository.save(newTask);
		Task savedTask = taskRepository.findBytaskId(TASK_ID);
		
		assertNotNull(savedTask);
		assertEquals(TASK_MANDATORY, savedTask.isIsMandatory());
		assertEquals(TASK_TAG, savedTask.getTag());
		assertEquals(TASK_CATEGORY, savedTask.getCategory());
		assertEquals(TASK_POINTS, savedTask.getPointCount());
		assertEquals(TASK_NAME, savedTask.getName());
		assertEquals(TASK_DESCRIPTION, savedTask.getDescription());
		assertEquals(TASK_ID, savedTask.getTaskId());
	
	}
	
}
