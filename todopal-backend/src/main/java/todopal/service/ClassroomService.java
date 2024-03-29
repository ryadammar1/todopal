package todopal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.ClassroomRepository;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Teacher;

@Service
public class ClassroomService {
	private final String NULL_CLASSROOM_EXCEPTION = "[error] Classroom with id does not exist";
	private final String EMPTY_STRING_EXCEPTION = "[error] String argument is empty";
	private final String ALREADY_EXIST_EXCEPTION = "Classroom with same name already created";

	@Autowired
	ClassroomRepository classroomRepository;

	@Transactional
	public List<String> getAllClassroomStudentsNames(Classroom originalClassroom) {
		if (originalClassroom == null) {
			throw new IllegalArgumentException("Inexistent Classroom");
		}
		Classroom classroom = classroomRepository.findByClassroomId(originalClassroom.getClassroomId());

		if (classroom == null) {
			throw new IllegalArgumentException("Inexistent Classroom");
		}

		Set<Student> studentsInClass = classroom.getStudent();
		if (studentsInClass.isEmpty()) {
			throw new IllegalArgumentException("Class is empty!");

		}
		ArrayList<String> studentNames = new ArrayList<String>();

		for (Student student : studentsInClass) {
			studentNames.add(student.getName());
		}

		return toList(studentNames);
	}

	@Transactional
	public Classroom createClassroom(Teacher teacher, String name, String imagePath, String subject) {
		checkForEmptyString(name);
		checkForEmptyString(imagePath);
		checkForEmptyString(subject);

		long classId = generateUniqueClassId();

		Classroom classroom = new Classroom();
		classroom.setClassroomId(classId);
		classroom.setTeacher(teacher);
		classroom.setName(name);
		classroom.setImagePath(imagePath);
		classroom.setSubject(subject);

		if (classroomRepository.findByNameAndTeacherEmail(name, teacher.getEmail()) != null) {
			throw new IllegalArgumentException(ALREADY_EXIST_EXCEPTION);
		}

		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomName(long id, String name) throws IllegalArgumentException {
		checkForEmptyString(name);

		Classroom classroom = getClassroom(id);
		classroom.setName(name);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomDescription(long id, String description) throws IllegalArgumentException {
		checkForEmptyString(description);

		Classroom classroom = getClassroom(id);
		classroom.setDescription(description);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomSubject(long id, String subject) throws IllegalArgumentException {
		checkForEmptyString(subject);

		Classroom classroom = getClassroom(id);
		classroom.setSubject(subject);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomImage(long id, String imagePath) throws IllegalArgumentException {
		checkForEmptyString(imagePath);

		Classroom classroom = getClassroom(id);
		classroom.setImagePath(imagePath);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskCategories(long id, ArrayList<String> taskCategories)
			throws IllegalArgumentException {
		Classroom classroom = getClassroom(id);
		classroom.setTaskCategories(taskCategories);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskTags(long id, ArrayList<String> taskTags) throws IllegalArgumentException {
		Classroom classroom = getClassroom(id);
		classroom.setTaskTags(taskTags);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom addToClassroomTaskTag(long id, String taskTag) throws IllegalArgumentException {
		checkForEmptyString(taskTag);

		Classroom classroom = getClassroom(id);
		classroom.getTaskTags().add(taskTag);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom addToClassroomTaskCategories(long id, String taskCategory) throws IllegalArgumentException {
		checkForEmptyString(taskCategory, "Category name is not provided");

		Classroom classroom = getClassroom(id);

		if (classroom.getTaskCategories() != null) {
			for (String categories : classroom.getTaskCategories()) {
				if (categories.equals(taskCategory)) {
					throw new IllegalArgumentException("Task category already exists");
				}
			}
			classroom.getTaskCategories().add(taskCategory);
		} else {
			ArrayList<String> categ = new ArrayList<>();
			categ.add(taskCategory);
			classroom.setTaskCategories(categ);
		}

		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom deleteClassroom(long id){
		Classroom classroom = classroomRepository.findByClassroomId(id);
		if (classroom == null) {
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);
		}
		classroomRepository.delete(classroom);
		return classroom;
	}

	@Transactional
	public Classroom updateClassroom(Classroom classroom) {
		if (classroomRepository.findByClassroomId(classroom.getClassroomId()) == null) {
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);
		}
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom getClassroom(long id) {
		Classroom classroom = classroomRepository.findByClassroomId(id);
		if (classroom == null) {
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);
		}
		return classroom;
	}

	@Transactional
	public Classroom getClassroom(String teacherEmail, String classroomName) {
		return classroomRepository.findByNameAndTeacherEmail(teacherEmail, classroomName);
	}

	@Transactional
	public Iterable<Classroom> getAllClassrooms() {
		return toArrayList(classroomRepository.findAll());
	}

	private <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
		ArrayList<T> resultList = new ArrayList<T>();
		iterable.forEach(resultList::add);
		return resultList;
	}

	private long generateUniqueClassId() {
		Random rand = new Random();
		long id = 0;

		do {
			id = rand.nextInt(9000000) + 1000000;
		} while (classroomRepository.findByClassroomId(id) != null);
		return id;
	}

	private void checkForEmptyString(String parameterValue) {
		if (parameterValue.trim().length() == 0) {
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);
		}
	}

	private void checkForEmptyString(String parameterValue, String errorMessage) {
		if (parameterValue.trim().length() == 0) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		iterable.forEach(resultList::add);
		return resultList;
	}
}
