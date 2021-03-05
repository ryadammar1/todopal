package todopal.features;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.controller.TodopalRestController;
import todopal.dao.TaskRepository;
import todopal.model.Student;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.TaskStatus;
import todopal.service.StudentService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TestDenyStudentTaskCompletion {

    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TaskService taskService;

    @Given("a task {string} is marked as {string} by {string}, with email {string}")
    public void a_task_is_marked_as_by_with_email(String taskName, String status, String studentName, String studentEmail) {
        Student student = studentService.createStudent(studentName, studentEmail, "1234");
        taskService.createTask(1, taskName, " ", "math","math", true, 0, LocalDate.now(), LocalDate.now());
        Set<TaskContainer> tasks = new HashSet<>();
        tasks.add(taskService.createTaskContainer(1, LocalDate.now(), TaskStatus.valueOf(status.toUpperCase()), 1, "u r da bst"));
        student.setSchoolTask(tasks);
        studentService.updateStudent(student);
    }

    @Given("task {string} is worth {string} points")
    public void task_is_worth_points(String taskName, String points) {
        try {
            Task task = taskService.getTask(1);
            task.setPointCount(Integer.parseInt(points));
            taskService.updateTask(task.getTaskId(), task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("teacher {string} is logged in with email {string}")
    public void teacher_is_logged_in_with_email(String name, String email) {
        teacherService.createTeacher("123", name, email, "123", "I am teacher and I exist");
    }

    @When("{string} marks {string} as incomplete for student {string} with message {string}")
    public void marks_as_incomplete_with_message(String teacher, String task, String studentEmail,  String message) {
        TaskContainer currentTask = null;
        Student student = studentService.getStudent(studentEmail);

        for(TaskContainer task1 : student.getSchoolTask()){

            System.out.println(task1.getTask().getName());
            if(task1.getTask().getName().equals(task)){
                currentTask = task1;
                break;
            }
        }

        currentTask.setStatus(TaskStatus.PROGRESS);
        currentTask.setFeedback(message);
        try {
            taskService.updateTaskContainer(currentTask.getTaskContainerId(), currentTask);
        } catch (Exception e) {
            Assert.fail();
        }
        Ressources.message = currentTask.getFeedback();
    }

    @Then("{string} will be marked as {string} for student {string}")
    public void will_be_marked_as(String taskName, String inProgress, String studentEmail) {
        TaskContainer currentTask = null;
        Student student = studentService.getStudent(studentEmail);
        for(TaskContainer task1 : student.getSchoolTask()){
            if(task1.getTask().getName().equals(taskName)){
                currentTask = task1;
                break;
            }
        }
        Assertions.assertNotNull(currentTask);
        Assertions.assertEquals(currentTask.getStatus(), TaskStatus.PROGRESS);
    }

    @Then("the message {string} will be displayed in the task details")
    public void the_message_will_be_displayed_in_the_task_details(String string) {
        Assert.assertEquals(string, Ressources.message);
    }
}
