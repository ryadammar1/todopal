package todopal.features;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import todopal.controller.TodopalRestController;
import todopal.model.Student;
import todopal.model.Teacher;
import todopal.service.StudentService;

public class TestCreateStudentAccount {
    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private StudentService studentService;

    @Given("an unregistered student wants to register for a new account")
    public void an_unregistered_student_wants_to_register_for_a_new_account() {

    }

    @When("user {string} with email {string} registers for a student account with name {string} and password {string}")
    public void user_with_email_registers_for_a_student_account_with_name_and_password(String name, String email,
            String accountName, String password) {
        try {
            studentService.createStudent(name, email, password);
        } catch (Exception e) {
            Ressources.message = e.getMessage();
        }
    }

    @Then("a student account for {string} is created under the email {string} with name {string} and password {string}")
    public void a_student_account_for_is_created_under_the_email_with_name_and_password(String email, String email2, String name, String password) {
        boolean exists = false;

        Student student = studentService.getStudent(email);
        if(student != null){
            exists = true;
        }

        Assertions.assertTrue(exists);
    }


    @Given("a registered student {string} with email {string} wants to register for a new account")
    public void a_registered_student_with_email_wants_to_register_for_a_new_account(String name, String email) {
        try {
            studentService.createStudent(name, email, "1234");
        } catch (Exception e) {
            throw new AssertionError();
        }
    }
}
