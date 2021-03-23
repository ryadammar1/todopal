package todopal.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.dao.ClassroomRepository;
import todopal.dao.TaskRepository;
import todopal.model.Classroom;
import todopal.model.Task;
import todopal.model.Teacher;
import todopal.service.ClassroomService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TeskDeleteTask {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ClassroomRepository classroomRepository;


    @Given("{string} has a class {string}")
    public void has_a_class(String teacherName, String className) {
        Teacher teacher = teacherService.getTeacher(teacherName+"@gmail.com");
        if(teacher == null) fail();
        try{
            Ressources.classroomId = classroomService.createClassroom(teacher, className, "/", "math").getClassroomId();
        }catch (Exception e){

            try{
                classroomService.deleteClassroom(Ressources.classroomId);
            }catch (Exception ee){ }

            Ressources.classroomId = classroomService.createClassroom(teacher, className, "/", "math").getClassroomId();
        }
    }

    @Given("the following tasks exist within class {string} for teacher {string}")
    public void the_following_tasks_exist_within_class_for_teacher(String className, String teacherName, io.cucumber.datatable.DataTable dataTable) {

        System.out.println(teacherName+"@gmail.com");
        Classroom classroom = classroomService.getClassroom(Ressources.classroomId);
        Set<Task> tasks = new HashSet<>();
        if(classroom == null) fail();
        List<Map<String, String>> tasksInfo = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> taskInfo : tasksInfo) {
            try {
                Task task = taskService.createTask((long)taskInfo.get("description").hashCode(), taskInfo.get("name"), taskInfo.get("description"), taskInfo.get("tag"), taskInfo.get("list"), true, Integer.parseInt(taskInfo.get("points")), taskInfo.get("start date"), taskInfo.get("due date"));
                tasks.add(task);
            } catch (Exception e) {
                Ressources.message = e.getMessage();

            }
        }
        classroom.setTask(tasks);
        classroomService.updateClassroom(classroom);
    }

    @When("task {string} is deleted from class {string} for teacher {string}")
    public void task_is_deleted_from_class_for_teacher(String taskName, String className, String teacherName) {
        try{
            taskService.deleteTask(taskName, className, teacherName+"@gmail.com");
        }catch (Exception e){
            Ressources.message = e.getMessage();
        }
    }
    @When("task {string} and {string} is deleted from class {string} for teacher {string}")
    public void task_and_is_deleted_from_class_for_teacher(String taskName, String taskName2, String className, String teacherName) {
        try{
            taskService.deleteTask(taskName, className, teacherName+"@gmail.com");
            taskService.deleteTask(taskName2, className, teacherName+"@gmail.com");
        }catch (Exception e){
            Ressources.message = e.getMessage();
        }
    }

    @Then("there are no more tasks in class {string} for teacher {string}")
    public void there_are_no_more_tasks_in_class(String className, String teacherName) {
        Classroom classroom = classroomService.getClassroom(Ressources.classroomId);
        if(!(classroom.getTask() == null || classroom.getTask().size() == 0)){
            fail();
        }
    }


    @Then("only one task remains in class {string} for teacher {string}")
    public void only_one_tasks_in_class(String className, String teacherName) {
        Classroom classroom = classroomService.getClassroom(Ressources.classroomId);
        System.out.println(classroom.getTask().size());
        if(classroom.getTask().size() != 1){
            fail();
        }
    }


}
