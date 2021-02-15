package todopal.features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todopal.dao.ClassroomRepository;
import todopal.dao.TaskContainerRepository;
import todopal.dao.TaskRepository;
import todopal.dao.TeacherRepository;

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

    @Before
    public void beforeScenario() {
        System.out.println("------------- Starting scenario -------------");
    }

    @After
    public void afterScenario() {

        System.out.println("Clearing database for next scenario");

        taskContainerRepository.deleteAll();
        taskRepository.deleteAll();
        classroomRepository.deleteAll();
        teacherRepository.deleteAll();
    }

}