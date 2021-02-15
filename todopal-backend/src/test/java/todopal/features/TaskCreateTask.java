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

public class TaskCreateTask {
    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TaskService taskService;

    Exception errorThrown = new IllegalArgumentException("");

    @Given("teacher {string} is logged in")
    public void teacher_is_logged_in(String string) {
        teacherService.createTeacher("123", string, "email@email.email", "password", "bio");
    }

    @Given("There are no tasks for teacher {string} in the mandatory list {string}")
    public void there_are_no_tasks_for_teacher_in_the_mandatory_list(String string, String string2) {
    }

    @Given("the following tasks exist")
    public void the_following_tasks_exist(io.cucumber.datatable.DataTable dataTable) {
        a_task_with_the_following_information_is_created(dataTable);
    }
    
    @Given("{string} has a mandatory list {string}")
    public void has_a_mandatory_list(String string, String string2) {
    }


    @When("a task with the following information is created")
    public void a_task_with_the_following_information_is_created(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> tasksInfo = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> taskInfo : tasksInfo) {
            try {
                taskService.createTask((long)taskInfo.get("description").hashCode(), taskInfo.get("name"), taskInfo.get("description"), taskInfo.get("tag"), taskInfo.get("list"), true, Integer.parseInt(taskInfo.get("points")), taskInfo.get("start date"), taskInfo.get("due date"));
            } catch (Exception e) {
                Ressources.message = e.getMessage();

            }
        }
    }

    // @Then("an {string} message is issued")
    // public void an_message_is_issued(String string) {
    //     Assertions.assertEquals("", errorThrown.getMessage());
    // }

    @Then("the following tasks exist now")
    public void the_following_tasks_exist_now(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> tasksInfo = dataTable.asMaps(String.class, String.class);
        List<Task> tasks = new ArrayList<Task>();
        for (Task task : taskService.getAllTasks()) {
            tasks.add(task);
        }
        boolean found = false;
        for (Map<String, String> taskInfo : tasksInfo) {
            for (Task task : tasks) {
                System.err.println(task.getName());
                if (taskInfo.get("name").equals(task.getName())) {
                    found = true;
                    break;
                }
            }
        }
        Assertions.assertTrue(found);
    }

}
