package todopal.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.TaskContainerRepository;
import todopal.dao.TaskRepository;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskContainerRepository taskContainerRepository;	
	
	@Transactional
	public Task createTask(long id, boolean isMandatory, String tag, String category, int pointCount, 
			String name, String description, Date startDate, Date dueDate) {
		Task task = new Task();

		task.setTaskId(id);
		task.setIsMandatory(isMandatory);
		task.setTag(tag);
		task.setCategory(category);
		task.setPointCount(pointCount);
		task.setName(name);
		task.setDescription(description);
		task.setStartDate(startDate);
		task.setDueDate(dueDate);
		
		taskRepository.save(task);

		return task;
	}
	
	
	@Transactional
	public Task createTask(long id, boolean isMandatory, String tag, String category, int pointCount, 
			String name, String description) {
		
		Task task = new Task();
		
		task.setTaskId(id);
		task.setIsMandatory(isMandatory);
		task.setTag(tag);
		task.setCategory(category);
		task.setPointCount(pointCount);
		task.setName(name);
		task.setDescription(description);
		
		taskRepository.save(task);

		return task;
	}
	
	
	@Transactional
	public TaskContainer createTaskContainer(long id, Date completionDate, TaskStatus status, Task task) {
		TaskContainer taskContainer = new TaskContainer();
		
		System.out.println("Hello");

		taskContainer.setCompletionDate(completionDate);
		taskContainer.setStatus(status);
		taskContainer.setTask(task);
		taskContainer.setTaskContainerId(id);
		
		taskContainerRepository.save(taskContainer);

		return taskContainer;
	}
		
	
	@Transactional
	public Task getTask(long id) throws Exception {
		
		Task task;
		try {
			task = taskRepository.findBytaskId(id);
		} catch (Exception e) {
			throw new Exception("Invalid Task Id");
		}
		
		return task;
	}
	
	@Transactional
	public TaskContainer getTaskContainer(long id) throws Exception {
		
		TaskContainer taskContainer;
		
		try {
			taskContainer = taskContainerRepository.findBytaskContainerId(id);
		} catch (Exception e) {
			throw new Exception("Invalid Task Container Id");
		}
		
		return taskContainer;
	}

	@Transactional
	public Iterable<Task> getAllTasks() {
		return toArrayList(taskRepository.findAll());
	}
	
	@Transactional
	public Iterable<TaskContainer> getAllTaskContainers() {
		return toArrayList(taskContainerRepository.findAll());
	}

	private <T> ArrayList<T> toArrayList(Iterable<T> iterable){
		ArrayList<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
