package todopal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.StudentRepository;
import todopal.dao.TaskContainerRepository;
import todopal.dao.TaskRepository;
import todopal.model.Student;
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
	private StudentRepository studentRepository;

	@Transactional
	public Task createTask(long taskId, String name, String description, String tag, String category,
			boolean isMandatory, int pointCount, LocalDate startDate, LocalDate dueDate) {
		if (taskRepository.findBytaskId(taskId) != null) {
			throw new IllegalArgumentException("The task was already created");
		} else if (isEmptyString(name)) {
			throw new IllegalArgumentException("Tasks cannot have an empty name");
		} else if (pointCount < 0) {
			throw new IllegalArgumentException("Tasks cannot have negative point values");
		} else if (startDate == null || dueDate == null) {
			throw new IllegalArgumentException("Tasks cannot have due null dates");
		} else if (dueDate.isBefore(startDate)) {
			throw new IllegalArgumentException("Tasks cannot have due date before the starting date");
		}

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
			boolean isMandatory, int pointCount, String startDate, String dueDate) {

		LocalDate localStartDate = isEmptyString(startDate) ? LocalDate.now() : LocalDate.parse(startDate);
		LocalDate localDueDate = isEmptyString(dueDate) ? LocalDate.parse("9999-12-12") : LocalDate.parse(dueDate);

		return createTask(taskId, name, description, tag, category, isMandatory, pointCount, localStartDate,
				localDueDate);
	}

	@Transactional
	public Task updateTask(long taskId, Task task) throws Exception {
		Task oldTask = getTask(taskId);
		oldTask = task;
		taskRepository.save(oldTask);
		return oldTask;
	}

	@Transactional
	public TaskContainer createTaskContainer(long id, LocalDate completionDate, TaskStatus status, long taskId,
			String feedback) {
		if (taskContainerRepository.findBytaskContainerId(id) != null) {
			throw new IllegalArgumentException("The task container was already created");
		} else if (taskRepository.findBytaskId(taskId) == null) {
			throw new IllegalArgumentException("Invalid Task Id");
		} else if (status == null) {
			throw new IllegalArgumentException("Task container cannot have null status");
		} else if (isEmptyString(feedback)) {
			throw new IllegalArgumentException("Task Container cannot have empty feedback");
		}

		TaskContainer taskContainer = new TaskContainer();
		Task task = taskRepository.findBytaskId(taskId);

		taskContainer.setCompletionDate(completionDate);
		taskContainer.setStatus(status);
		taskContainer.setTask(task);
		taskContainer.setTaskContainerId(id);
		taskContainer.setFeedback(feedback);

		taskContainerRepository.save(taskContainer);
		return taskContainer;
	}

	@Transactional
	public TaskContainer updateTaskContainer(long taskContainerId, TaskContainer taskContainer) throws Exception {
		TaskContainer oldTaskContainer = getTaskContainer(taskContainerId);
		oldTaskContainer = taskContainer;
		taskContainerRepository.save(oldTaskContainer);
		return oldTaskContainer;
	}

	@Transactional
	public TaskContainer denyTask(long taskContainerId, String studentEmail) throws Exception {
		TaskContainer taskContainer = getTaskContainer(taskContainerId);
		Student student = getStudentWithTask(taskContainerId, studentEmail);

		taskContainer.setStatus(TaskStatus.PROGRESS);
		taskContainer.setCompletionDate(null);
		taskContainerRepository.save(taskContainer);
		studentRepository.save(student);

		return taskContainer;
	}

	@Transactional
	public TaskContainer approveTask(long taskContainerId, String studentEmail) throws Exception {
		TaskContainer taskContainer = getTaskContainer(taskContainerId);
		Student student = getStudentWithTask(taskContainerId, studentEmail);

		taskContainer.setStatus(TaskStatus.CLOSED);
		taskContainer.setCompletionDate(LocalDate.now());
		taskContainerRepository.save(taskContainer);
		student.setTotalPoints(student.getTotalPoints() + taskContainer.getTask().getPointCount());
		studentRepository.save(student);

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
	private Student getStudentWithTask(long taskContainerId, String studentEmail) throws Exception {
		if (studentRepository.findStudentByEmail(studentEmail) == null) {
			throw new IllegalArgumentException("Non-existant Student");
		}
		Student student = studentRepository.findStudentByEmail(studentEmail);
		Set<TaskContainer> schoolTasks = student.getSchoolTask();
		Set<TaskContainer> personalTasks = student.getPersonalTask();

		if (!checkTaskContainer(schoolTasks, taskContainerId) && !checkTaskContainer(personalTasks, taskContainerId)) {
			throw new IllegalArgumentException("The specified student doesn't have this task");
		}

		return student;
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
		iterable.forEach(resultList::add);
		return resultList;
	}

	private boolean isEmptyString(String value) {
		return (value == null || value.trim().length() == 0);
	}

	private boolean checkTaskContainer(Set<TaskContainer> taskContainers, long taskContainerId) {
		for (TaskContainer task : taskContainers) {
			if (task.getTaskContainerId() == taskContainerId) {
				return true;
			}
		}
		return false;
	}
}
