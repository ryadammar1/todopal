package todopal.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import todopal.model.*;
import todopal.service.ClassroomService;
import todopal.service.StudentService;
import todopal.service.TaskService;
import todopal.service.TeacherService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestViewStudentProfile {

    @Autowired
    ClassroomService classroomService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    TaskService taskService;

    @Given("{string} have selected classroom with classroom id {int}")
    public void they_have_selected_classroom_with_classroom_id(String email, Integer id) {
        Ressources.classroomId = classroomService.createClassroom(teacherService.getTeacher(email), "e", "./", "math").getClassroomId();
    }

    @Given("The following student accounts exist class {int}")
    public void the_following_student_accounts_exist_class(Integer id, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        List<List<String>> studentInfos = dataTable.asLists();

        for(List<String> student : studentInfos){
            if(student.get(2).equals("Points") == true) continue;
            Student currStudent = studentService.createStudent(student.get(0), student.get(0)+"@gmail.com", "1234");
            currStudent.setClassroom(classroomService.getClassroom(Ressources.classroomId));
            System.out.println(student.get(2));
            currStudent.setTotalPoints(Integer.parseInt(student.get(2).trim()));
            Task task = taskService.createTask(Integer.parseInt(student.get(2).trim()), student.get(3),"e","e","e",false, Integer.parseInt(student.get(2)), LocalDate.now().toString(), "");
            TaskContainer taskContainer = taskService.createTaskContainer(Integer.parseInt(student.get(2).trim()), LocalDate.now(), TaskStatus.DONE, Integer.parseInt(student.get(2).trim()), "nice");
            Set<TaskContainer> taskContainerSet = new HashSet<>();
            taskContainerSet.add(taskContainer);
            currStudent.setSchoolTask(taskContainerSet);
            studentService.updateStudent(currStudent);
        }
    }

    @When("they view the student profile of student {string} for classroom {int}")
    public void they_view_the_student_profile_of_student_for_classroom(String name, Integer int1) {
        Set<Student> students = classroomService.getClassroom(Ressources.classroomId).getStudent();
        for(Student currStudent : students){
            if(currStudent.getName().equals(name)){
                Ressources.student = currStudent;
                return;
            }
        }
        Assertions.fail();
    }

    @Then("the profile information is provided")
    public void the_profile_information_is_provided(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<List<String>> studentInfos = dataTable.asLists();
        boolean hasTask = false;

        Assertions.assertEquals(Ressources.student.getTotalPoints(), Integer.parseInt(studentInfos.get(1).get(2).trim()));
        Assertions.assertEquals(Ressources.student.getName(), studentInfos.get(1).get(0));
        for(TaskContainer currContainer : Ressources.student.getSchoolTask()){
            if(currContainer.getTask().getName().equals(studentInfos.get(1).get(3))){
                hasTask = true;
                break;
            }
        }
        Assertions.assertTrue(hasTask);

    }
}
