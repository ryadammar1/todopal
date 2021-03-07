package todopal.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.service.StudentService;
import todopal.service.TeacherService;

public class TestLogin {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Given("teacher {string} with email {string} and password {string} wants to login as {string}")
    public void teacher_with_email_and_password_wants_to_login_as(String name, String email, String password, String role) {
        try{
            if(role.equals("teacher")){
                teacherService.createTeacher("123", name, email, password, "I teach math");
            }else{
                studentService.createStudent(name, email, password);
            }
        }catch(Exception e){
            Assertions.fail();
        }
    }

    @When("teacher {string} attemps to login with email {string} and password {string}")
    public void teacher_attemps_to_login_with_email_and_password(String name, String email, String password) {
        try{
            Ressources.teacher = teacherService.logInTeacher(email, password);
        }catch (Exception e){
            Ressources.message = e.getMessage();
        }

    }

    @Then("the teacher will be logged in")
    public void the_teacher_will_be_logged_in() {
       Assertions.assertNotNull(Ressources.teacher);
    }

    @Given("student {string} with email {string} and password {string} wants to login as {string}")
    public void student_with_email_and_password_wants_to_login_as(String name, String email, String password, String role) {
        teacher_with_email_and_password_wants_to_login_as(name, email, password, role);
    }

    @When("student {string} attemps to login with email {string} and password {string}")
    public void student_attemps_to_login_with_email_and_password(String name, String email, String password) {
        try{
            Ressources.student = studentService.logInStudent(email, password);
        }catch (Exception e){
            Ressources.message = e.getMessage();
        }
    }

    @Then("the student will be logged in")
    public void the_student_will_be_logged_in() {
        Assertions.assertNotNull(Ressources.student);
    }

}
