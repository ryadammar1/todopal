package todopal.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import todopal.controller.TodopalRestController;
import todopal.dao.TaskRepository;
import todopal.model.Task;
import todopal.service.ClassroomService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

public class TestConfirmStudentTaskCompletion {
    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TaskService taskService;

    Exception errorThrown = new IllegalArgumentException("");

    @Given("a task {string} is marked as {string} by {string}, with email {string}")
    public void a_task_is_marked_as_by_with_email(String string, String string2, String string3, String string4) {
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
