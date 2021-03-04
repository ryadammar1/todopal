package todopal.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Given("teacher <name> with teacher email <email> is logged in")
    public void teacherNameWithTeacherEmailEmailIsLoggedIn(String name, String email) {
        System.out.println(name + email);
    }

    @When("teacher <name> creates a term-long optional task list with list name <list_name>")
    public void teacherNameCreatesATermLongOptionalTaskListWithListNameList_name() {
    }

    @Then("the term long optional task list with list name <list_name> is created under teacher <name>")
    public void theTermLongOptionalTaskListWithListNameList_nameIsCreatedUnderTeacherName() {
    }

    @And("weekly optional task list with list name <list_name> exists")
    public void weeklyOptionalTaskListWithListNameList_nameExists() {
    }

    @Given("teacher {string} with teacher email {string} is logged in")
    public void teacherWithTeacherEmailIsLoggedIn(String arg0, String arg1) {
    }

    @When("teacher {string} creates a weekly optional task list with list name {string}")
    public void teacherCreatesAWeeklyOptionalTaskListWithListName(String arg0, String arg1) {
    }
}
