package todopal.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.controller.TodopalRestController;
import todopal.service.ClassroomService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

public class TestCreateOptionalList {

    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TaskService taskService;

    Exception errorThrown = new IllegalArgumentException("");

    @When("teacher {string} creates a term-long optional task list with list name {string}")
    public void teacherJohnBobCreatesATermLongOptionalTaskListWithListNameMath_work(String email, String listName) {
        try {
            teacherService.addToOptionalLists(email, listName);
        } catch (Exception e) {
            Ressources.message = e.getMessage();
        }
    }

    @Then("the term long optional task list with list name {string} is created under teacher {string}")
    public void theTermLongOptionalTaskListWithListNameMath_workIsCreatedUnderTeacherJohnBob(String listName, String email) {
            String list = teacherService.getTeacher(email).getOptionalLists().get(0);
        Assertions.assertEquals(listName, list);
    }

    @And("weekly optional task list with list name {string} exists for teacher {string}")
    public void weeklyOptionalTaskListWithListNameMath_workExistsForTeacherJohnBobGmailCom(String listName, String email) {
        try {
            teacherService.addToOptionalLists(email, listName);
        } catch (Exception e) {
            Ressources.message = e.getMessage();
        }
    }
}
