package todopal.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import todopal.controller.TodopalRestController;
import todopal.dao.StudentRepository;
import todopal.dao.TaskContainerRepository;
import todopal.model.Student;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;
import todopal.service.StudentService;
import todopal.service.TaskService;

public class TestMarkTaskCompletion {
	@Autowired
	TodopalRestController todopalRestController;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TaskService taskService;

    private Student student;
	private Task task;
	private TaskContainer taskContainer;

	private void createStudentAndTask(String studentEmail, String taskName) {
		student = studentService.createStudent("studentName", studentEmail, "password");
		task = taskService.createTask(1, taskName, "description", "tag", "category", true, 1, LocalDate.now(), LocalDate.now());
		taskContainer = taskService.createTaskContainer(1, LocalDate.now(), TaskStatus.PROGRESS, task.getTaskId(), "Finish it!");
	}

	@Given("an incomplete school task {string} has been completed by student {string} with email {string}")
	public void an_incomplete_school_task_has_been_completed_by_student_with_email(String taskName, String studentName, String studentEmail) {
		createStudentAndTask(studentEmail, taskName);
		final Set<TaskContainer> schoolTasks = student.getSchoolTask();
		schoolTasks.add(taskContainer);
		studentService.updateStudent(student);
	}

	@Given("an incomplete personal task {string} has been completed by student {string} with email {string}")
	public void an_incomplete_personal_task_has_been_completed_by_student_with_email(String taskName, String studentName, String studentEmail) {
		createStudentAndTask(studentEmail, taskName);
		final Set<TaskContainer> personalTasks = student.getPersonalTask();
		personalTasks.add(taskContainer);
		studentService.updateStudent(student);
	}

	@When("student {string} marks the task as complete")
	public void student_marks_as_complete(String studentName) {
		taskContainer = taskService.setTaskAsDone(task.getTaskId(), student.getEmail(), "You're lazy");
	}

	@Then("the {string} is marked as {string}")
	public void the_task_is_marked_as(String taskName, String taskStatus) {
		assertEquals(TaskStatus.valueOf(taskStatus.toUpperCase()), taskContainer.getStatus());
	}
}
