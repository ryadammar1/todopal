package todopal.features;

import io.cucumber.core.resource.Resource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.model.*;
import todopal.service.ClassroomService;
import todopal.service.StudentService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

public class TestViewTodoList {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TaskService taskService;

    @Given("the following tasks exist for {string} in class {string}")
    public void the_following_tasks_exist_for(String string, String string2,
            io.cucumber.datatable.DataTable dataTable) {

        Student student = studentService.getStudent(string);
        Teacher teacher = teacherService.createTeacher("123", "Bob", "e@e.e", "1234", "i am a teacher");
        Classroom classroom = classroomService.createClassroom(teacher, string2, "/", "math");

        Set<Task> tasks = new HashSet<>();
        Set<TaskContainer> studentTasks = new HashSet<>();
        if (classroom == null) {
            fail();
        }
        List<Map<String, String>> tasksInfo = dataTable.asMaps(String.class, String.class);

        Random random = new Random();

        for (Map<String, String> taskInfo : tasksInfo) {
            try {
                Task task = taskService.createTask(taskInfo.get("TaskName").hashCode(), taskInfo.get("TaskName"), "",
                        "", "", true, 0, LocalDate.now(), LocalDate.of(2025, 03, 03));


                studentTasks.add(taskService.createTaskContainer(random.nextInt(), LocalDate.now(),
                        TaskStatus.valueOf(taskInfo.get("Status")), taskInfo.get("TaskName").hashCode(), "Good"));
                tasks.add(task);
            } catch (Exception e) {
                Ressources.message = e.getMessage();

            }
        }

        student.setClassroom(classroom);
        classroom.setTask(tasks);
        student.setSchoolTask(studentTasks);

        studentService.updateStudent(student);
        classroomService.updateClassroom(classroom);
    }

    @When("user {string} views his todolist")
    public void user_views_his_todolist(String string) {
        try {
            Set<TaskContainer> tasks = studentService.getStudentSchoolTasks(string);
            Ressources.message = "";
            for(TaskContainer taskContainer : tasks)
            {
                Ressources.message += taskContainer.getTask().getName() + ",";
            }
        } catch (Exception e) {
            Ressources.message = e.getMessage();
        }

    }

    @Then("the following todo list is returned {string}")
    public void the_following_todo_list_is_returned(String string) {

        for (String sequence : string.split(","))
        {
            if(!Ressources.message.contains(sequence))
            {
                Assertions.fail();
            }
        }
    }

    @Given("user {string} is not part of a classroom")
    public void user_is_not_part_of_a_classroom(String string) {

    }

}
