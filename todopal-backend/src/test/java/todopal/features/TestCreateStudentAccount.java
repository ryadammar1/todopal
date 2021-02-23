package todopal.features;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.controller.TodopalRestController;
import todopal.model.Student;
import todopal.service.ClassroomService;
import todopal.service.StudentService;

public class TestCreateStudentAccount {
    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;

    @Given("an unregistered student wants to register for a new account")
    public void an_unregistered_student_wants_to_register_for_a_new_account() {

    }


    @When("user {string} with email {string} registers for a student account with name {string} and password {string}")
    public void user_with_email_registers_for_a_student_account_with_name_and_password(String name, String email, String accountName, String password) {
        try{
            Student student = studentService.createStudent(name, email, password);
            System.out.println(student.getEmail());
        }catch (Exception e){
            Ressources.message = e.getMessage();
        }
    }

    @Given("a registered student {string} with email {string} wants to register for a new account")
    public void a_registered_student_with_email_wants_to_register_for_a_new_account(String name, String email) {
        try{
            studentService.createStudent(name, email, "1234");
        }catch (Exception e){
            throw new AssertionError();
        }
    }
}
