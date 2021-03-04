package todopal.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todopal.dto.ClassroomDto;
import todopal.dto.StudentDto;
import todopal.dto.TaskContainerDto;
import todopal.dto.TaskDto;
import todopal.dto.TeacherDto;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;
import todopal.model.Teacher;
import todopal.service.ClassroomService;
import todopal.service.StudentService;
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
	@Autowired
	private StudentService studentService;

	@PostMapping(value = { "/create-task", "/create-task/" })
	public TaskDto createTask(@RequestParam("id") long taskId, @RequestParam("mandatory") boolean isMandatory,
			@RequestParam("tag") String tag, @RequestParam("category") String category,
			@RequestParam("points") int pointCount, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("start-date") String startDate,
			@RequestParam("due-date") String dueDate) throws Exception {
		Task task = taskService.createTask(taskId, name, description, tag, category, isMandatory, pointCount, startDate,
				dueDate);
		return Converter.convertToDto(task);
	}

	@PostMapping(value = { "/create-task/{startDate}/{dueDate}", "/create-task/{startDate}/{dueDate}/" })
	public TaskDto createTaskWithDate(@RequestParam("id") long taskId, @RequestParam("mandatory") boolean isMandatory,
			@RequestParam("tag") String tag, @RequestParam("category") String category,
			@RequestParam("points") int pointCount, @RequestParam("name") String name,
			@RequestParam("description") String description, @PathVariable("startDate") String startDate,
			@PathVariable("dueDate") String dueDate) throws Exception {

		LocalDate realStartDate = LocalDate.parse(startDate);
		LocalDate realDueDate = LocalDate.parse(dueDate);
		Task task = taskService.createTask(taskId, name, description, tag, category, isMandatory, pointCount,
				realStartDate, realDueDate);

		return Converter.convertToDto(task);
	}

	@PostMapping(value = { "/create-task-container", "/create-task-container/" })
	public TaskContainerDto createTaskContainer(@RequestParam("id") long taskContainerId,
			@RequestParam("date") String completionDate, @RequestParam("status") TaskStatus status,
			@RequestParam("taskId") long taskId, @RequestParam("feedback") String feedback) throws Exception {

		LocalDate realCompletionDate = LocalDate.parse(completionDate);
		TaskContainer taskContainer = taskService.createTaskContainer(taskContainerId, realCompletionDate, status,
				taskId, feedback);
		return Converter.convertToDto(taskContainer);
	}

	@PostMapping(value = { "/create-classroom/{name}", "/create-classroom/{name}/" })
	public ClassroomDto createClassroom(@RequestParam("teacherEmail") String teacherEmail,
			@RequestParam("imagePath") String imagePath, @RequestParam("subject") String subject,
			@PathVariable("name") String name) throws Exception {
		Teacher teacher = teacherService.getTeacher(teacherEmail);
		Classroom classroom = classroomService.createClassroom(teacher, name, imagePath, subject);
		return Converter.converDto(classroom);
	}

	@GetMapping(value = { "/task", "/task/" })
	public TaskDto getTask(@RequestParam("id") long taskId) throws Exception {
		Task task = taskService.getTask(taskId);
		return Converter.convertToDto(task);
	}

	@GetMapping(value = { "/all-tasks", "/all-tasks/" })
	public List<TaskDto> getTasks() throws Exception {
		List<TaskDto> tasks = new ArrayList<TaskDto>();
		taskService.getAllTasks().forEach(taskEntry -> tasks.add(Converter.convertToDto(taskEntry)));
		return tasks;
	}

	@GetMapping(value = { "/task-container", "/task-container/" })
	public TaskContainerDto getTaskContainer(@RequestParam("id") long taskContainerId) throws Exception {
		TaskContainer taskContainer = taskService.getTaskContainer(taskContainerId);
		return Converter.convertToDto(taskContainer);
	}

	@GetMapping(value = { "/all-task-containers", "/all-task-containers/" })
	public List<TaskContainerDto> getTaskContainers() throws Exception {
		List<TaskContainerDto> taskContainers = new ArrayList<TaskContainerDto>();
		taskService.getAllTaskContainers().forEach(container -> taskContainers.add(Converter.convertToDto(container)));
		return taskContainers;
	}

	@PostMapping(value = { "/teachers/{name}", "/teachers/{name}/" })
	public TeacherDto createTeacher(@RequestParam("approvalCode") String appCode, @PathVariable("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("bio") String bio) throws IllegalArgumentException {

		Teacher teacher = teacherService.createTeacher(appCode, name, email, password, bio);
		return Converter.convertToDto(teacher);
	}
	
	@PostMapping(value = { "deny/{email}/{task}", "/deny/{email}/{task}" })
	public TaskContainerDto denyTask(@PathVariable("email") String email, @PathVariable("task") long taskId)
			throws Exception {
		TaskContainer taskContainer = taskService.denyTask(taskId, email);
		return Converter.convertToDto(taskContainer);
	}

	@GetMapping(value = { "/teachers/{email}", "/teachers/{email}/" })
	public TeacherDto getTeacherByName(@PathVariable("email") String email) throws IllegalArgumentException {
		return Converter.convertToDto(teacherService.getTeacher(email));
	}

	@GetMapping(value = { "/teachers", "/teachers/" })
	public List<TeacherDto> getAllTeachers() {
		List<TeacherDto> teachers = new ArrayList<>();
		teacherService.getAllTeachers().forEach(teacher -> teachers.add(Converter.convertToDto(teacher)));
		return teachers;
	}

	@PostMapping(value = { "/students/{name}", "/studentss/{name}/" })
	public StudentDto createStudent(@PathVariable("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password) throws IllegalArgumentException {
		Student student = studentService.createStudent(name, email, password);
		return Converter.convertToDto(student);
	}

	@GetMapping(value = { "/students/{email}", "/students/{email}/" })
	public StudentDto getStudentByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
		Student student = studentService.getStudent(email);
		return Converter.convertToDto(student);
	}

	@GetMapping(value = { "/students", "/students/" })
	public List<StudentDto> getAllStudents() {
		List<StudentDto> students = new ArrayList<>();
		studentService.getAllStudents().forEach(student -> students.add(Converter.convertToDto(student)));
		return students;
	}

	@PostMapping(value = { "/create-task-list/optional/{name}", "/create-task-list/optional/{name}/" })
	public TeacherDto createOptionalList(@RequestParam("teacherEmail") String teacherEmail, @PathVariable("name") String name) {
		Teacher teacher = teacherService.addToOptionalLists(teacherEmail, name);
		return Converter.convertToDto(teacher);
	}
}