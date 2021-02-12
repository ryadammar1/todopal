package todopal.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTaskContainerPersistence {
	
	@Autowired
	private TaskContainerRepository taskContainerRepository;
	@Autowired
	private TaskRepository taskRepository;
	
	@AfterEach
	public void clearDatabase() {
		taskContainerRepository.deleteAll();
		taskRepository.deleteAll(); 
	}
	
	
	@Test
	public void testPersistAfterSave() {
		
		TaskContainer taskContainer = new TaskContainer(); 
        
        Task task = new Task();
        task.setTaskId(2);
        taskRepository.save(task);
        
        taskContainer.setStatus(TaskStatus.TODO);
        taskContainer.setTaskContainerId(1);
        taskContainer.setTask(task);
        
		taskContainerRepository.save(taskContainer);
		
		TaskContainer savedTaskContainer = taskContainerRepository.findBytaskContainerId(1);
		
		assertNotNull(savedTaskContainer);
		assertEquals(TaskStatus.TODO, savedTaskContainer.getStatus());
		assertEquals(1, savedTaskContainer.getTaskContainerId());
		assertEquals(2, savedTaskContainer.getTask().getTaskId());
		
	
	}
	
}
