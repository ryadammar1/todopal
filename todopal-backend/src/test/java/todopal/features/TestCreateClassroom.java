/**
 * @author Ricky
 *
 * I am very unsure about this code. Can we review this?
 */

package todopal.features;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.controller.TodopalRestController;
import todopal.model.Classroom;
import todopal.model.Teacher;
import todopal.service.ClassroomService;
import todopal.service.TeacherService;

import java.util.HashSet;
import java.util.Set;

public class TestCreateClassroom {

    @Autowired
    TodopalRestController todopalRestController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassroomService classroomService;

    Teacher teacher = new Teacher();
    Exception errorThrown = new IllegalArgumentException("");

    @Given("user {string} with {string} is logged in as a teacher")
    public void user_name_with_email_is_logged_in_as_a_teacher(String name, String email) {
        //REPLACE WITH TEACHER LOGIN WHEN POSSIBLE
        String approvalCode = "123";
        String password = "123";
        String bio = "Hello!";
        teacher = teacherService.createTeacher(approvalCode, name, email, password, bio);
    }

    @When("{string} creates a classroom with name {string} and image with path {string} and subject {string}")
    public void createsAClassroomWithName(String name, String classroomName, String imagePath, String subject) {
        try {
            todopalRestController.createClassroom(teacher.getEmail(), imagePath, subject, classroomName);
        } catch (Exception e) {
            errorThrown = e;
        }
    }

    @Then("a new classroom with name {string} and a randomized unique {int}-digit classroom id is created")
    public void aNewClassroomWithNameAndARandomizedUniqueDigitClassroomIdIsCreated(String classroomName, int classroomId) {
        Classroom classroom = classroomService.getClassroom(classroomId);

        Assertions.assertEquals(classroomName, classroom.getName());
        //how about the id assertion?
    }

    @Given("{string} is responsible for classroom {string}")
    public void isResponsibleForClassroom(String name, String classroomName) {
        HashSet<Classroom> classrooms = new HashSet<>();
        Classroom classroom = classroomService.getClassroom(name, classroomName);
        classrooms.add(classroom);
        teacher.setClassroom(classrooms);
    }

    @Then("the new classroom is not created")
    public void theNewClassroomIsNotCreated() {
        Assertions.assertEquals(1, teacher.getClassroom().size());
    }

    @Then("a {string} message is issued")
    public void aMessageIsIssued(String classroomAlreadyCreatedError) {
        Assertions.assertEquals(classroomAlreadyCreatedError, errorThrown);
    }
}
