package todopal.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todopal.dto.ClassroomDto;
import todopal.dto.PersonDto;
import todopal.dto.TaskContainerDto;
import todopal.dto.TaskDto;
import todopal.dto.TeacherDto;
import todopal.model.Classroom;
import todopal.model.Person;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;
import todopal.model.Teacher;
import todopal.service.ClassroomService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

@RestController
public class TodopalRestController {
	
	@Autowired
	private ClassroomService classroomService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(value = {"/create-task", "/create-task/"})
	  public TaskDto createTask(@RequestParam("id") long taskId, @RequestParam("mandatory") boolean isMandatory,
			  @RequestParam("tag") String tag,  @RequestParam("category") String category,
			  @RequestParam("points") int pointCount,  @RequestParam("name") String name,
			  @RequestParam("description") String description) throws Exception {
	    Task task = taskService.createTask(taskId, isMandatory, tag, category, pointCount, name, description);
	    return convertToDto(task);
	  }
	
	@PostMapping(value = {"/create-task/{startDate}/{dueDate}", "/create-task/{startDate}/{dueDate}/"})
	  public TaskDto createTaskWithDate(@RequestParam("id") long taskId, @RequestParam("mandatory") boolean isMandatory,
			  @RequestParam("tag") String tag,  @RequestParam("category") String category,
			  @RequestParam("points") int pointCount,  @RequestParam("name") String name,
			  @RequestParam("description") String description, @PathVariable("startDate") Date startDate,
			  @PathVariable("dueDate") Date dueDate) throws Exception {
	    Task task = taskService.createTask(taskId, isMandatory, tag, category, pointCount, name, description,
	    		startDate, dueDate);
	    return convertToDto(task);
	  }
	
	@PostMapping(value = {"/create-task-container", "/create-task-container/"})
	  public TaskContainerDto createTaskContainer(@RequestParam("id") long taskContainerId, 
			  @RequestParam("date") Date completionDate,  @RequestParam("status") TaskStatus status,
			  @RequestParam("taskId") long taskId) throws Exception {
	    TaskContainer taskContainer = taskService.createTaskContainer(taskContainerId, completionDate, status, taskId);
	    return convertToDto(taskContainer);
	  }

	@PostMapping(value = { "/create-classroom/{name}", "/create-classroom/{name}/" })
	public ClassroomDto createClassroom(@RequestParam("teacherEmail") String teacherEmail, @RequestParam("imagePath") String imagePath, 
			  @RequestParam("subject") String subject, @PathVariable("name") String name) throws Exception {
		Teacher teacher = teacherService.getTeacher(teacherEmail);
		Classroom classroom = classroomService.createClassroom(teacher, name, imagePath, subject);
		return converDto(classroom);
	}
	
	@GetMapping(value = { "/task", "/task/" })
	public TaskDto getTask(@RequestParam("id") long taskId) throws Exception {
		Task task = taskService.getTask(taskId);
		return convertToDto(task);
	}
	
	@GetMapping(value = { "/all-tasks", "/all-tasks/" })
	public List<TaskDto> getTasks() throws Exception {
		List<TaskDto> tasks = new ArrayList<TaskDto>();
		for (Task task : taskService.getAllTasks()) {
			tasks.add(convertToDto(task));
		}
		return tasks;
	}
	
	@GetMapping(value = { "/task-container", "/task-container/" })
	public TaskContainerDto getTaskContainer(@RequestParam("id") long taskContainerId) throws Exception {
		TaskContainer taskContainer = taskService.getTaskContainer(taskContainerId);
		return convertToDto(taskContainer);
	}
	
	@GetMapping(value = { "/all-task-containers", "/all-task-containers/" })
	public List<TaskContainerDto> getTaskContainers() throws Exception {
		List<TaskContainerDto> taskContainers = new ArrayList<TaskContainerDto>();
		for (TaskContainer taskContainer : taskService.getAllTaskContainers()) {
			taskContainers.add(convertToDto(taskContainer));
		}
		return taskContainers;
	}
	
	
//	@PostMapping(value = { "/persons/{name}", "/persons/{name}/" })
//	public TeacherDto createPerson(@PathVariable("name") String name) throws IllegalArgumentException {
//		
//		Teacher teacher = service.createTeacher(name);
//		return convertToDto(teacher);
//	}
//	@GetMapping(value = { "/persons/{name}", "/person/{name}/" })
//	public TeacherDto getPersonByName(@PathVariable("name") String name) throws IllegalArgumentException {
//		return convertToDto(service.getTeacher(name));
//	}
//	
//	@PostMapping(value = { "/teachers/{name}", "/teachers/{name}/" })
//	public TeacherDto createTeacher(@PathVariable("name") String name) throws IllegalArgumentException {
//		
//		Teacher teacher = service.createTeacher(name);
//		return convertToDto(teacher);
//	}
//	@GetMapping(value = { "/teachers/{name}", "/teachers/{name}/" })
//	public TeacherDto getTeacherByName(@PathVariable("name") String name) throws IllegalArgumentException {
//		return convertToDto(service.getTeacher(name));
//	}
//	@GetMapping(value = { "/persons", "/persons/" })
//	public List<PersonDto> getAllPersons() {
//		List<PersonDto> persons = new ArrayList<>();
//		for (Person person : service.getAllPersons()) {
//			persons.add(convertToDto(person));
//		}
//		return persons;
//	}
	
	private PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getName());
		return personDto;
	}
	
	private TeacherDto convertToDto(Teacher t) {
		if (t == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		TeacherDto teacherDto = new TeacherDto(t.getName());
		return teacherDto;
	}
	
	private TaskDto convertToDto(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("There is no such Task!");
		}
		TaskDto taskDto = new TaskDto(task.getTaskId(), task.isIsMandatory(), task.getTag(), task.getCategory(),
				task.getPointCount(), task.getName(), task.getDescription(), task.getStartDate(), task.getDueDate());
		return taskDto;
	}
	
	private TaskContainerDto convertToDto(TaskContainer taskContainer) {
		if (taskContainer == null) {
			throw new IllegalArgumentException("There is no such Task Container!");
		}
		TaskDto taskDto = convertToDto(taskContainer.getTask());
		TaskContainerDto taskContainerDto = new TaskContainerDto(taskContainer.getTaskContainerId(), 
				taskContainer.getCompletionDate(), taskContainer.getStatus(), taskDto);
		return taskContainerDto;
	}

	private ClassroomDto converDto(Classroom classroom) {
		if (classroom == null) {
			throw new IllegalArgumentException("There is no such Classroom!");
		}
		TeacherDto teacherDto = convertToDto(classroom.getTeacher());
		ClassroomDto classroomDto = new ClassroomDto(classroom.getName(), teacherDto, classroom.getImagePath(), classroom.getSubject(), classroom.getClassroomId());
		return classroomDto;
	}

}
