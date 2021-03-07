package todopal.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.model.Classroom;
import todopal.service.ClassroomService;
import todopal.service.TeacherService;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestCreateTaskCategory {

    //+1h

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Given("teacher {string} with teacher email {string} is logged in")
    public void teacher_with_teacher_email_is_logged_in(String name, String email) {
        try{
            teacherService.createTeacher("123", name, email, "password", "bio empty");
        }catch (Exception e){
            Assertions.fail();
        }
    }
    @When("teacher {string} creates a weekly mandatory task list with list name {string} for classroom {string}")
    public void teacher_creates_a_task_list_with_list_name_for_classroom(String email, String name, String classroom) {
        try {
            Classroom ecseClass = classroomService.createClassroom(teacherService.getTeacher(email), classroom, "IMAGEPATH", "Software engineering");
            Ressources.classroomId = ecseClass.getClassroomId();
        }catch (IllegalArgumentException e){

        }

        try{
            classroomService.addToClassroomTaskCategories(Ressources.classroomId, name);
        }catch (Exception e){
            Ressources.message = e.getMessage();
        }

    }

    @Then("the weekly mandatory task list with list name {string} is created under teacher {string} for classroom {string}")
    public void the_task_list_with_list_name_is_created_under_teacher_for_classroom(String name, String email, String classroom) {
        boolean check = false;
        Classroom ecseClass = classroomService.getClassroom(Ressources.classroomId);

        if(ecseClass.getTaskCategories() == null){
        Assertions.fail();
        }

        for(String e: ecseClass.getTaskCategories()){
            if(e.equals(name)){
               check = true;
            }
        }

        Assertions.assertTrue(check);
    }

    @Given("a weekly mandatory task list with list name {string} exists under teacher {string} for classroom {string}")
    public void a_task_list_with_list_name_exists_under_teacher_for_classroom(String name, String email, String classroom) {
        Classroom ecseClass = classroomService.createClassroom(teacherService.getTeacher(email), classroom, "IMAGEPATH", "Software engineering");
        Ressources.classroomId = ecseClass.getClassroomId();

        try{
            classroomService.addToClassroomTaskCategories(Ressources.classroomId, name);
        }catch (Exception e){
           Assertions.fail();
        }
    }



}
