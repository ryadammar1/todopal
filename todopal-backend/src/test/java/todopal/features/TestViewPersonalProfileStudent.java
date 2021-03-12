package todopal.features;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import todopal.controller.TodopalRestController;
import todopal.dto.StudentDto;
import todopal.model.Student;
import todopal.service.StudentService;

public class TestViewPersonalProfileStudent {
	
	@Autowired
    TodopalRestController todopalRestController;

    @Autowired
    StudentService studentService;
    Student student;

    StudentDto response;
	
	@Given("student {string} is logged in with email {string} and password {string} and total points {string}")
	public void student_is_logged_in_with_grade_and_age_and_total_points_and_email(String studentName, String email, String password, String points) {
	    // Write code here that turns the phrase above into concrete actions
	    student = studentService.createStudent(studentName, email, password, Integer.parseInt(points));
	}

	@When("student {string} is accessing his personal profile")
	public void student_is_accessing_his_personal_profile(String email) {
	    // Write code here that turns the phrase above into concrete actions
	    response = todopalRestController.getStudentByEmailWithPersonalInfo(email);
	}

	@Then("the name of {string} will be displayed")
	public void the_name_of_will_be_displayed(String name) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(name, response.getName());
	}

	@Then("the number of total points {string} of {string} wll be displayed")
	public void the_number_of_total_points_of_wll_be_displayed(String points, String fullname) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(Integer.parseInt(points), response.getTotalPoints());
		
	}

	@Then("the email {string} associated with {string} will be displayed")
	public void the_email_associated_with_will_be_displayed(String email, String fullname) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(email, response.getEmail());
	}

}
