package todopal.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.transaction.annotation.Transactional;
import todopal.model.Classroom;
import todopal.model.Task;
import todopal.model.Teacher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTaskPersistence {

	private final boolean TASK_MANDATORY = true;
	private final String TASK_TAG = "MATH";
	private final String TASK_CATEGORY = "HOMEWORK";
	private final int TASK_POINTS = 5;
	private final String TASK_NAME = "MATH PROBLEM";
	private final String TASK_DESCRIPTION = "DO PROBLEM 1.1";
	private final long TASK_ID = 1;
	private final String TASK_START_DATE = "2021-02-13";
	private final String TASK_DUE_DATE = "2021-02-16";

	private final String TEACHER_NAME = "NAME_TEST";
	private final String TEACHER_EMAIL = "name.test@yahoo.ca";
	private final String TEACHER_BIO = "I love pen island!";
	private final String TEACHER_APPROVAL_CODE = "911";
	private final String CLASS_NAME = "NAME_TEST";
	private final long CLASS_ID = 1;

	private ArrayList<String> TASK_CATEGORIES = new ArrayList<String>();

	// @Autowired
	// private TeacherRepository teacherRepository;

	// @Autowired
	// private ClassroomRepository classroomRepository;

	@Autowired
	private TaskRepository taskRepository;

	@AfterEach
	public void clearDatabase() {
		taskRepository.deleteAll();
		// classroomRepository.deleteAll();
		// teacherRepository.deleteAll();
	}

	/*
	 * private Teacher createTeacher() { final Teacher teacher = new Teacher();
	 * 
	 * teacher.setEmail(TEACHER_EMAIL); teacher.setName(TEACHER_NAME);
	 * teacher.setBio(TEACHER_BIO); teacher.setApprovalCode(TEACHER_APPROVAL_CODE);
	 * 
	 * return teacherRepository.save(teacher); }
	 * 
	 * private Classroom createClassroom() { Classroom classroom = new Classroom();
	 * 
	 * TASK_CATEGORIES.add("Math"); TASK_CATEGORIES.add("English");
	 * TASK_CATEGORIES.add("Physics");
	 * 
	 * classroom.setClassroomId(CLASS_ID); classroom.setName(CLASS_NAME);
	 * classroom.setTaskCategories(TASK_CATEGORIES);
	 * 
	 * return classroomRepository.save(classroom); }
	 */

	@Test
	public void testPersistAfterSave() {

		Task newTask = new Task();

		newTask.setIsMandatory(TASK_MANDATORY);
		newTask.setTag(TASK_TAG);
		newTask.setCategory(TASK_CATEGORY);
		newTask.setPointCount(TASK_POINTS);
		newTask.setName(TASK_NAME);
		newTask.setDescription(TASK_DESCRIPTION);
		newTask.setTaskId(TASK_ID);

		LocalDate realStartDate = LocalDate.parse(TASK_START_DATE);
		newTask.setStartDate(realStartDate);

		LocalDate realDueDate = LocalDate.parse(TASK_DUE_DATE);
		newTask.setDueDate(realDueDate);

		taskRepository.save(newTask);
		Task savedTask = taskRepository.findBytaskId(TASK_ID);

		assertNotNull(savedTask);
		assertEquals(TASK_MANDATORY, savedTask.isIsMandatory());
		assertEquals(TASK_TAG, savedTask.getTag());
		assertEquals(TASK_CATEGORY, savedTask.getCategory());
		assertEquals(TASK_POINTS, savedTask.getPointCount());
		assertEquals(TASK_NAME, savedTask.getName());
		assertEquals(TASK_DESCRIPTION, savedTask.getDescription());
		assertEquals(TASK_ID, savedTask.getTaskId());
		assertEquals(realStartDate, savedTask.getStartDate());
		assertEquals(realDueDate, savedTask.getDueDate());

	}

	@Test
	@Transactional
	public void testDeleteById() {

		Task newTask = new Task();

		newTask.setIsMandatory(TASK_MANDATORY);
		newTask.setTag(TASK_TAG);
		newTask.setCategory(TASK_CATEGORY);
		newTask.setPointCount(TASK_POINTS);
		newTask.setName(TASK_NAME);
		newTask.setDescription(TASK_DESCRIPTION);
		newTask.setTaskId(TASK_ID);

		LocalDate realStartDate = LocalDate.parse(TASK_START_DATE);
		newTask.setStartDate(realStartDate);

		LocalDate realDueDate = LocalDate.parse(TASK_DUE_DATE);
		newTask.setDueDate(realDueDate);

		taskRepository.save(newTask);
		Task savedTask = taskRepository.deleteTaskByTaskId(TASK_ID);

		assertNull(savedTask);

	}

	/*
	 * @Test public void testFindByClassAndName() {
	 * 
	 * Task newTask = new Task();
	 * 
	 * Classroom classroom = createClassroom(); Teacher teacher = createTeacher();
	 * 
	 * newTask.setIsMandatory(TASK_MANDATORY); newTask.setTag(TASK_TAG);
	 * newTask.setCategory(TASK_CATEGORY); newTask.setPointCount(TASK_POINTS);
	 * newTask.setName(TASK_NAME); newTask.setDescription(TASK_DESCRIPTION);
	 * newTask.setTaskId(TASK_ID);
	 * 
	 * LocalDate realStartDate = LocalDate.parse(TASK_START_DATE);
	 * newTask.setStartDate(realStartDate);
	 * 
	 * LocalDate realDueDate = LocalDate.parse(TASK_DUE_DATE);
	 * newTask.setDueDate(realDueDate);
	 * 
	 * Set<Task> tasks = new HashSet<>(); tasks.add(newTask);
	 * 
	 * classroom.setTask(tasks);
	 * 
	 * Set<Classroom> classrooms = new HashSet<>(); classrooms.add(classroom);
	 * 
	 * teacher.setClassroom(classrooms);
	 * 
	 * taskRepository.save(newTask); classroomRepository.save(classroom);
	 * teacherRepository.save(teacher);
	 * 
	 * Task savedTask =
	 * taskRepository.findTaskByNameAndAndClassroom(newTask.getName(),
	 * classroomRepository.findByNameAndTeacherEmail(classroom.getName(),
	 * teacher.getName()));
	 * 
	 * assertNotNull(savedTask); assertEquals(TASK_MANDATORY,
	 * savedTask.isIsMandatory()); assertEquals(TASK_TAG, savedTask.getTag());
	 * assertEquals(TASK_CATEGORY, savedTask.getCategory());
	 * assertEquals(TASK_POINTS, savedTask.getPointCount()); assertEquals(TASK_NAME,
	 * savedTask.getName()); assertEquals(TASK_DESCRIPTION,
	 * savedTask.getDescription()); assertEquals(TASK_ID, savedTask.getTaskId());
	 * assertEquals(realStartDate, savedTask.getStartDate());
	 * assertEquals(realDueDate, savedTask.getDueDate());
	 * 
	 * }
	 */

}
