package todopal.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import todopal.service.TeacherService;
import todopal.controller.TodopalRestController;
import todopal.dto.TeacherDto;

public class TestViewPersonalProfileTeacher {
    @Autowired
    TodopalRestController todopalRestController;

    @Autowired
    TeacherService teacherService;

    TeacherDto response;

    @Given("teacher {string} is logged in with name {string} email {string}, password {string} and bio {string}")
    public void teacher_is_logged_in_with_name_email_and_password(String fullname, String username, String email, String password, String bio) {
	teacherService.createTeacher("approvalCode", username, email, password, bio);
    }


    @When("teacher {string} is accessing his personal profile")
    public void teacher_is_accessing_his_personal_profile(String username) {
	response = todopalRestController.getTeacherByName(username);
    }

    @Then("the name of {string} will be displayed")
    public void the_name_of_will_be_displayed(String name) {
	assertEquals(name, response.getName());
    }

    @Then("the email {string} associated with {string} will be displayed")
    public void the_email_associated_with_will_be_displayed(String email, String fullname) {
	assertEquals(email, response.getEmail());
    }

    @Then("all the classrooms are displayed")
    public void all_the_classrooms_are_displayed() {
	//throw new io.cucumber.java.PendingException();
    }

    @Then("the bio displays: {string}")
    public void the_bio_displays(String bio) {
	assertEquals(bio, response.getBio());
    }

}
