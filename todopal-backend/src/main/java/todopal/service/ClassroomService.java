package todopal.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.ClassroomRepository;
import todopal.dao.TeacherRepository;
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

		Random rand = new Random();

		long id = rand.nextInt(9000000) + 1000000;

		while (classroomRepository.findByClassroomId(id) != null)
			id = rand.nextInt(9000000) + 1000000;

		if (name.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);
		if (imagePath.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);
		if (subject.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.setClassroomId(id);
		classroom.setTeacher(teacher);
		classroom.setName(name);
		classroom.setImagePath(imagePath);
		classroom.setSubject(subject);

		if (classroomRepository.findByNameAndTeacherEmail(name, teacher.getEmail()) != null) {
			throw new IllegalArgumentException(ALREADY_EXIST_EXCEPTION);
		} else {
			classroomRepository.save(classroom);
			return classroom;
		}
	}

	@Transactional
	public Classroom setClassroomName(long id, String name) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		if (name.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.setName(name);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomDescription(long id, String description) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		if (description.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.setDescription(description);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomSubject(long id, String subject) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		if (subject.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.setSubject(subject);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomImage(long id, String imagePath) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		if (imagePath.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.setImagePath(imagePath);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskCategories(long id, ArrayList<String> taskCategories)
			throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		classroom.setTaskCategories(taskCategories);

		classroomRepository.save(classroom);

		return classroom;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Classroom addToClassroomTaskCategories(long id, String taskCategory) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		if (taskCategory.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.getTaskCategories().add(taskCategory);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskTags(long id, ArrayList<String> taskTags) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		classroom.setTaskTags(taskTags);

		classroomRepository.save(classroom);

		return classroom;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Classroom addToClassroomTaskTag(long id, String taskTag) throws IllegalArgumentException {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		if (classroom == null)
			throw new IllegalArgumentException(NULL_CLASSROOM_EXCEPTION);

		if (taskTag.length() == 0)
			throw new IllegalArgumentException(EMPTY_STRING_EXCEPTION);

		classroom.getTaskTags().add(taskTag);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom getClassroom(long id) {
		return classroomRepository.findByClassroomId(id);
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

}
