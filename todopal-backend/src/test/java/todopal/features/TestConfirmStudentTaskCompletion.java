package todopal.features;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
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

public class TestConfirmStudentTaskCompletion {
    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TaskContainerRepository taskContainerRepository;

    @Given("there is a class task called {string} that is worth {string} points")
    public void there_is_a_class_task_called_that_is_worth_points(String taskName, String points) {
        final Task task = taskService.createTask(1, taskName, "", "chem", "mandatory", true, Integer.parseInt(points),
                LocalDate.now(), LocalDate.parse("9999-12-12"));
        taskService.createTaskContainer(1, null, TaskStatus.PROGRESS, task.getTaskId());
    }

    @Given("a task {string} is marked as {string} by {string}, with email {string}")
    public void a_task_is_marked_as_by_with_email(String taskName, String status, String studentName,
            String studentEmail) {
        try {
            final TaskContainer taskContainer = taskService.getTaskContainer(1);
            final Student student = studentService.createStudent(studentName, studentEmail, "password");

            Set<TaskContainer> schoolTasks = student.getSchoolTask();
            schoolTasks.add(taskContainer);
            studentRepository.save(student);

            taskContainer.setStatus(TaskStatus.valueOf(status.toUpperCase()));
            taskContainerRepository.save(taskContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("a task {string} is not marked as done by {string}, with email {string}")
    public void a_task_is_not_marked_as_done_by_with_email(String taskName, String studentName, String studentEmail) {
        try {
            TaskContainer taskContainer = taskService.getTaskContainer(1);
            final Student student = studentService.createStudent(studentName, studentEmail, "password");

            Set<TaskContainer> schoolTasks = student.getSchoolTask();
            schoolTasks.add(taskContainer);
            studentRepository.save(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("{string} marks {string} as officially complete")
    public void marks_as_officially_complete(String teacherName, String taskName) throws Exception {
        todopalRestController.approveTask("taha@pattomail.com", 1);
    }

    @Then("{string} will be marked as {string}")
    public void will_be_marked_as(String taskName, String taskStatus) {
        try {
            TaskContainer taskContainer = taskService.getTaskContainer(1);
            Assertions.assertEquals(TaskStatus.valueOf(taskStatus.toUpperCase()), taskContainer.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("{string} points will be added to the tally of student {string}")
    public void points_will_be_added_to_the_tally_of_student(String point, String studentName) {
        try {
            Student student = studentService.getStudent("taha@pattomail.com");
            Assertions.assertEquals(Integer.parseInt(point), student.getTotalPoints());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
