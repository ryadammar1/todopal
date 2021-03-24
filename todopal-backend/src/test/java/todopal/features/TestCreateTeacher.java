package todopal.features;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.*;
import todopal.controller.TodopalRestController;
import todopal.model.Student;
import todopal.model.Teacher;
import todopal.service.StudentService;
import todopal.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;

public class TestCreateTeacher {
	
	 @Autowired
	 TodopalRestController todopalRestController;
	 @Autowired
	 private TeacherService teacherService;
	 @Autowired
	 private StudentService studentService;
	 
	 @Given("an unregistered teacher wants to register for a new account")
	 public void an_unregistered_teacher_wants_to_register_for_a_new_account() {
	     
	 }

	 @When("user {string} with email {string} registers for a teacher account with name {string} and password {string}")
	 public void user_with_email_registers_for_a_teacher_account_with_name_and_password(String name, String email, String name2, String password) {
		 try{
			 teacherService.createTeacher("123", name,email, password, "bio empty");
		 }catch (Exception e){
			 Ressources.message = e.getMessage();
		 }
	 }
	 
	 @Then("a teacher account for {string} is created under the email {string} with name {string} and password {string}")
	 public void a_teacher_account_for_is_created_under_the_email_with_name_and_password(String email, String email2, String name, String password) {
	 	boolean exists = false;

	     Teacher teacher = teacherService.getTeacher(email);
	     if(teacher != null){
	     	exists = true;
		 }

	     Assertions.assertTrue(exists);
	 }
	 
	 @Given("a registered teacher {string} with email {string} wants to register for a new account")
	 public void a_registered_teacher_with_email_wants_to_register_for_a_new_account(String name, String email) {
	 	try{
			teacherService.createTeacher("123", name,email, "password", "bio empty");
		}catch (Exception e){
	 		Ressources.message = e.getMessage();
		}

	 }

	 @Then("an {string} message is issued")
	 public void an_message_is_issued(String errorMessage) {
		 
	    Assertions.assertEquals(errorMessage, Ressources.message);
	    
	 }
	
	
}
