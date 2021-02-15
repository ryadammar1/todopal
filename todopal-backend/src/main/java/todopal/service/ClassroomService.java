package todopal.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.ClassroomRepository;
import todopal.model.Classroom;
import todopal.model.Teacher;

@Service
public class ClassroomService {

	@Autowired
	ClassroomRepository classroomRepository;

	private final String NULL_CLASSROOM_EXCEPTION = "[error] Classroom with id does not exist";
	private final String EMPTY_STRING_EXCEPTION = "[error] String argument is empty";
	private final String ALREADY_EXIST_EXCEPTION = "Classroom with same name already created";

	@Transactional
	public Classroom createClassroom(Teacher teacher, String name, String imagePath, String subject) {
		Classroom classroom = new Classroom();

		checkForEmptyString(name);
		checkForEmptyString(imagePath);
		checkForEmptyString(subject);

		long classId = generateUniqueClassId();
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
		Classroom classroom = classroomRepository.findByClassroomId(id);

		checkForEmptyString(name);

		classroom.setName(name);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomDescription(long id, String description) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		checkForEmptyString(description);

		classroom.setDescription(description);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomSubject(long id, String subject) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		checkForEmptyString(subject);

		classroom.setSubject(subject);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomImage(long id, String imagePath) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		checkForEmptyString(imagePath);

		classroom.setImagePath(imagePath);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskCategories(long id, ArrayList<String> taskCategories)
			throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);
		classroom.setTaskCategories(taskCategories);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom addToClassroomTaskCategories(long id, String taskCategory) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		checkForEmptyString(taskCategory);

		classroom.getTaskCategories().add(taskCategory);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskTags(long id, ArrayList<String> taskTags) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);
		classroom.setTaskTags(taskTags);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom addToClassroomTaskTag(long id, String taskTag) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		checkForEmptyString(taskTag);

		classroom.getTaskTags().add(taskTag);
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
	public Classroom getClassroom(String teacherName, String classroomName) {
		return classroomRepository.findByNameAndTeacherEmail(teacherName, classroomName);
	}

	@Transactional
	public Iterable<Classroom> getAllClassrooms() {
		return toArrayList(classroomRepository.findAll());
	}

	private <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
		ArrayList<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
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
}
