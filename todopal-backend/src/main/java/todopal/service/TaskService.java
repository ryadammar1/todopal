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
	public Task createTask(long taskId, String name, String description, String tag, String category,
			boolean isMandatory, int pointCount, LocalDate startDate, LocalDate dueDate) {

		Task task = new Task();

		task.setTaskId(taskId);
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
	public Task createTask(long taskId, String name, String description, String tag, String category,
			boolean isMandatory, int pointCount) {
		return createTask(taskId, name, description, tag, category, isMandatory, pointCount, null, null);
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
		if (task == null) {
			throw new Exception("Invalid Task Id");
		}

		return task;
	}

	@Transactional
	public TaskContainer getTaskContainer(long id) throws Exception {
		TaskContainer taskContainer = taskContainerRepository.findBytaskContainerId(id);
		if (taskContainer == null) {
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

	private <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
		ArrayList<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
