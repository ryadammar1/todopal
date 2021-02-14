package todopal.features;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.*;
import todopal.controller.TodopalRestController;
import todopal.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;

public class TestCreateTeacher {
	
	 @Autowired
	 TodopalRestController todopalRestController;
	 @Autowired
	 private TeacherService teacherService;
	
//	@Given("An unregistered teacher wants to register for a new account")
//	public void an_unregistered_teacher_wants_to_register_for_a_new_account() {
//		
//	}
//	
//	@When("user {String} with email {String} registers for a teacher account with name {String} and password {String}")
//	public void user_with_email_registers_for_a_teacher_account_with_name_and_password(String email, String name, String password) {
//		todopalRestController.createTeacher("123", name, email, password, "bio empty");
//	}
//	
//	@Then("Then an account for {String} is created under the email {String} with name <name> and password {String}")
//	public void 
	
}
