package todopal.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import todopal.service.ClassroomService;
import todopal.service.TeacherService;
import todopal.controller.TodopalRestController;
import todopal.dto.TeacherDto;
import todopal.dto.ClassroomDto;
import todopal.model.Teacher;

public class TestViewPersonalProfileTeacher {
    @Autowired
    TodopalRestController todopalRestController;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ClassroomService classroomService;

    Teacher teacher;
    TeacherDto response;

    @Given("teacher {string} is logged in with name {string} email {string}, password {string} and bio {string}")
    public void teacher_is_logged_in_with_name_email_and_password(String fullname, String username, String email,
            String password, String bio) {
        teacher = teacherService.createTeacher("approvalCode", username, email, password, bio);
    }

    @Given("{string} has classrooms: {string} and {string}")
    public void has_classrooms_and(String username, String classroom1, String classroom2) {
        classroomService.createClassroom(teacher, classroom1, "image", "math");
        classroomService.createClassroom(teacher, classroom2, "image", "math");
    }

    @When("teacher {string} is accessing his personal profile")
    public void teacher_is_accessing_his_personal_profile(String email) {
        response = todopalRestController.getTeacherPersonalInfromationByEmail(email);
    }

    @Then("the name of {string} will be displayed")
    public void the_name_of_will_be_displayed(String name) {
        assertEquals(name, response.getName());
    }

    @Then("the email {string} associated with {string} will be displayed")
    public void the_email_associated_with_will_be_displayed(String email, String fullname) {
        assertEquals(email, response.getEmail());
    }

    @Then("the classrooms {string} and {string} are displayed")
    public void the_classrooms_and_are_displayed(String classroom1, String classroom2) {
        assertEquals(2, response.getClassroom().size());

        boolean foundClassroom1 = false;
        boolean foundClassroom2 = false;
        for (ClassroomDto dto : response.getClassroom()) {
            if (dto.getClassroomName().equals(classroom1))
                foundClassroom1 = true;
            if (dto.getClassroomName().equals(classroom2))
                foundClassroom2 = true;
        }
        assertTrue(foundClassroom1, "Teacher is registered with " + classroom1);
        assertTrue(foundClassroom2, "Teacher is registered with " + classroom2);
    }

    @Then("the bio displays: {string}")
    public void the_bio_displays(String bio) {
        assertEquals(bio, response.getBio());
    }

}
