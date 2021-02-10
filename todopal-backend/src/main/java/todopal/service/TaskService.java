package todopal.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.ClassroomRepository;
import todopal.dao.TaskContainerRepository;
import todopal.dao.TaskRepository;
import todopal.model.Classroom;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskContainerRepository taskContainerRepository;
	@Autowired
	private ClassroomRepository classroomRepository;
	
	
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
	public TaskContainer createTaskContainer(long id, Date completionDate, TaskStatus status, Task task) {
		TaskContainer taskContainer = new TaskContainer();

		taskContainer.setCompletionDate(completionDate);
		taskContainer.setStatus(status);
		taskContainer.setTask(task);
		taskContainer.setTaskContainerId(id);
		
		taskContainerRepository.save(taskContainer);

		return taskContainer;
	}
	
	
	@Transactional
	public Task addTaskToClassroom(long taskId, long classroomId) {
		Task task = taskRepository.findBytaskId(taskId);
		Classroom classroom = classroomRepository.findByClassroomId(classroomId);
		
		Set<Task> classroomTasks = classroom.getTask();
		
		if(classroomTasks.isEmpty()) {
			Set<Task> newTasks = new HashSet<Task>();
			classroom.setTask(newTasks);
		} else {
			classroomTasks.add(task);
			classroom.setTask(classroomTasks);
		}
		task.setClassroom(classroom);
		
		taskRepository.save(task);
		classroomRepository.save(classroom);
		
		return task;	
	}
	
	@Transactional
	public TaskContainer setTaskContainerStatus(long id, TaskStatus status) {
		TaskContainer taskContainer = taskContainerRepository.findBytaskContainerId(id);

		taskContainer.setStatus(status);

		taskContainerRepository.save(taskContainer);

		return taskContainer;
	}
	
	
	@Transactional
	public Task setTaskIsMandatory(long id, boolean isMandatory) {
		Task task = taskRepository.findBytaskId(id);

		task.setIsMandatory(isMandatory);

		taskRepository.save(task);

		return task;
	}
	
	
	@Transactional
	public Task setTaskTag(long id, String tag) {
		Task task = taskRepository.findBytaskId(id);

		task.setTag(tag);

		taskRepository.save(task);

		return task;
	}
	
	@Transactional
	public Task setTaskPointCount(long id, int pointCount) {
		Task task = taskRepository.findBytaskId(id);

		task.setPointCount(pointCount);

		taskRepository.save(task);

		return task;
	}
	
	@Transactional
	public Task setTaskCategory(long id, String category) {
		Task task = taskRepository.findBytaskId(id);

		task.setCategory(category);

		taskRepository.save(task);

		return task;
	}
	
	@Transactional
	public Task setTaskName(long id, String name) {
		Task task = taskRepository.findBytaskId(id);

		task.setName(name);

		taskRepository.save(task);

		return task;
	}
	
	
	@Transactional
	public Task setTaskDescription(long id, String description) {
		Task task = taskRepository.findBytaskId(id);

		task.setDescription(description);

		taskRepository.save(task);

		return task;
	}
	
	@Transactional
	public Task setTaskDates(long id, Date startDate, Date dueDate) {
		Task task = taskRepository.findBytaskId(id);

		task.setStartDate(startDate);
		task.setDueDate(dueDate);

		taskRepository.save(task);

		return task;
	}
	
	
	@Transactional
	public Task getTask(long id) {
		return taskRepository.findBytaskId(id);
	}
	
	@Transactional
	public TaskContainer getTaskContainer(long id) {
		return taskContainerRepository.findBytaskContainerId(id);
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
