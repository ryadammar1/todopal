package todopal.features;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import todopal.controller.TodopalRestController;
import todopal.dao.TaskRepository;
import todopal.model.Student;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;
import todopal.model.Teacher;
import todopal.service.ClassroomService;
import todopal.service.StudentService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

public class TestConfirmStudentTaskCompletion {
    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TaskService taskService;

    Exception errorThrown = new IllegalArgumentException("");

    @Given("{string} created a classroom")
    public void created_a_classroom(String string) {
    }
    @Given("there is a class task called {string} that is worth {string} points")
    public void there_is_a_class_task_called_that_is_worth_points(String taskName, String points) {
    }
    @Given("a task {string} is marked as {string} by {string}, with email {string}")
    public void a_task_is_marked_as_by_with_email(String taskName, String status, String studentName,
            String studentEmail) {
    }
    @Given("task {string} is worth {string} points")
    public void task_is_worth_points(String string, String string2) {
    }
    @Given("a task {string} is not marked as done by {string}, with email {string}")
    public void a_task_is_not_marked_as_done_by_with_email(String string, String string2, String string3) {
    }

    @When("{string} marks {string} as officially complete")
    public void marks_as_officially_complete(String string, String string2) {
    }

    @Then("{string} will be marked as {string}")
    public void will_be_marked_as(String string, String string2) {
    }
    @Then("{string} points will be added to the tally of student {string}")
    public void points_will_be_added_to_the_tally_of_student(String string, String string2) {
    }
}
