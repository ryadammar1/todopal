package todopal.features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todopal.dao.*;
import todopal.model.Classroom;
import todopal.model.Teacher;

import java.util.HashSet;

@SpringBootTest(classes = CucumberTestsRunner.class)
public class CucumberHooks {

    @Autowired
    private TaskContainerRepository taskContainerRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void beforeScenario() {
        System.out.println("------------- Starting scenario -------------");
    }

    @After
    public void afterScenario() {

        System.out.println("Clearing database for next scenario");
        studentRepository.deleteAll();
        taskContainerRepository.deleteAll();
        taskRepository.deleteAll();
        for (Teacher t : teacherRepository.findAll()) {
            for (Classroom c : t.getClassroom()) {
                c.setTeacher(null);
                classroomRepository.save(c);
            }
            t.setClassroom(new HashSet<Classroom>());
            teacherRepository.save(t);
        }
        teacherRepository.deleteAll();
        classroomRepository.deleteAll();
    }

}
