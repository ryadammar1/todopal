package todopal.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import todopal.model.Student;
import todopal.model.TaskContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestStudentPersistence {
    private final String STUDENT_NAME = "NAME_TEST";
    private final String STUDENT_EMAIL = "name.test@yahoo.ca";
    private final String STUDENT_BIO = "I love pen island!";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @AfterEach
    public void clearDatabase() {
        studentRepository.deleteAll();
    }

    private Student createStudent() {
        Student student = new Student();

        student.setEmail(STUDENT_EMAIL);
        student.setName(STUDENT_NAME);
        student.setBio(STUDENT_BIO);

        return studentRepository.save(student);
    }

    @Test
    public void testPersistAfterSave() {
        createStudent();

        final Student savedStudent = studentRepository.findStudentByEmail(STUDENT_EMAIL);

        assertNotNull(savedStudent);
        assertEquals(STUDENT_NAME, savedStudent.getName());
        assertEquals(STUDENT_BIO, savedStudent.getBio());
    }

    @Test
    public void testUpdateStudent()
    {
        Student student = createStudent();

        ArrayList<String> taskCategories = new ArrayList<String>();
        taskCategories.add("task");

        ArrayList<String> taskTags = new ArrayList<String>();
        taskTags.add("tag");

        student.setName("name");
        student.setBio("bio");
        student.setTotalPoints(1);
        student.setTaskCategories(taskCategories);
        student.setTaskTags(taskTags);

        studentRepository.save(student);

        assertEquals("name", student.getName());
        assertEquals("bio", student.getBio());
        assertEquals(1, student.getTotalPoints());
        assertEquals("task", student.getTaskCategories().get(0));
        assertEquals("tag", student.getTaskTags().get(0));
    }
}
