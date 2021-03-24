package todopal.features;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Teacher;
import todopal.service.ClassroomService;
import todopal.service.StudentService;
import todopal.service.TeacherService;

public class TestViewAllStudents {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;

    @Given("the following students exist")
    public void the_following_students_exist(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> students = dataTable.asLists(String.class);

        for (int i = 1; i < students.size(); i++) {
            String name = students.get(i).get(0);
            String email = students.get(i).get(1);
            studentService.createStudent(name, email, "password");
        }
    }

    @Given("the following classroom exists")
    public void the_following_classroom_exists(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> classrooms = dataTable.asLists(String.class);

        for (int i = 1; i < classrooms.size(); i++) {
            String classroomName = classrooms.get(i).get(0);
            String teacherEmail = classrooms.get(i).get(1);
            String studentEmail = classrooms.get(i).get(2);

            Teacher teacher = teacherService.getTeacher(teacherEmail);
            Classroom classroom = classroomService.createClassroom(teacher, classroomName, "imagePath", "subject");

            if (studentEmail != null) {
                Student student = studentService.getStudent(studentEmail);
                student.setClassroom(classroom);

                HashSet<Student> studentSet = new HashSet<Student>();
                studentSet.add(student);
                classroom.setStudent(studentSet);

                studentService.updateStudent(student);
            }

            classroomService.updateClassroom(classroom);
        }
    }

    @Given("teacher {string} has no classroom")
    public void teacher_has_no_classroom(String string) {
        Teacher teacher = teacherService.getTeacher(string);
        teacher.setClassroom(null);
        teacherService.updateTeacher(teacher);
    }

    @When("teacher {string} views classroom {string}")
    public void teacher_views_classroom(String teacherEmail, String classroomName) {
        try {
            Teacher teacher = teacherService.getTeacher(teacherEmail);
            Classroom classroom = classroomService.getClassroom(classroomName, teacher.getEmail());
            Ressources.studentNames = classroomService.getAllClassroomStudentsNames(classroom);

        } catch (Exception e) {
            Ressources.message = e.getMessage();
        }
    }

    @Then("a list containing {string} is returned")
    public void a_list_containing_is_returned(String string) {
        Assertions.assertEquals(1, Ressources.studentNames.size());
        Assertions.assertEquals(string, Ressources.studentNames.get(0));
    }
}
