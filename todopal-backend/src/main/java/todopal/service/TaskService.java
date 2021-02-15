package todopal.service;

import java.time.LocalDate;
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
			String name, String description, LocalDate startDate, LocalDate dueDate) {
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
			String name, String description, String startDate, String dueDate) {


		if(taskRepository.findBytaskId(id) != null){
			throw new IllegalArgumentException("The task was already created");
		}

		if(name == null || name.trim().length()==0 ){
			throw new IllegalArgumentException("Tasks cannot have an empty name");
		}

		if(pointCount < 0){
			throw new IllegalArgumentException("Tasks cannot have negative point values");
		}

		if((dueDate != null && dueDate.trim().length() != 0) && (startDate != null && startDate.trim().length() !=0)
				&& LocalDate.parse(dueDate).isBefore(LocalDate.parse(startDate))){
			throw new IllegalArgumentException("Tasks cannot have due date before the starting date");
		}

		Task task = new Task();
		
		task.setTaskId(id);
		task.setIsMandatory(isMandatory);
		task.setTag(tag);
		task.setCategory(category);
		task.setPointCount(pointCount);
		task.setName(name);

		task.setDescription(description);
		if(startDate != null && startDate.trim().length() !=0){
			task.setStartDate(LocalDate.parse(startDate));
		}else{
			task.setStartDate(LocalDate.now());
		}

		if(dueDate != null && dueDate.trim().length() != 0){
			task.setDueDate(LocalDate.parse(dueDate));
		}else{
			task.setDueDate(LocalDate.parse("9999-12-12"));
		}

		taskRepository.save(task);

		return task;
	}
	
	
	@Transactional
	public TaskContainer createTaskContainer(long id, LocalDate completionDate, TaskStatus status, long taskId) {
		TaskContainer taskContainer = new TaskContainer();
		
		Task task = taskRepository.findBytaskId(taskId);
		
		taskContainer.setCompletionDate(completionDate);
		taskContainer.setStatus(status);
		taskContainer.setTask(task);
		taskContainer.setTaskContainerId(id);
		
		taskContainerRepository.save(taskContainer);

		return taskContainer;
	}
		
	
	@Transactional
	public Task getTask(long id) throws Exception {
		
		Task task = taskRepository.findBytaskId(id);
		
		if(task == null) {
			throw new Exception("Invalid Task Id");
		}
		
		return task;
	}
	
	@Transactional
	public TaskContainer getTaskContainer(long id) throws Exception {
		
		TaskContainer taskContainer = taskContainerRepository.findBytaskContainerId(id);
		
		if(taskContainer == null){
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
