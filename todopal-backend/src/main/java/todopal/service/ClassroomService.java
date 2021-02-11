package todopal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.ClassroomRepository;
import todopal.dao.TeacherRepository;
import todopal.model.Classroom;
import todopal.model.Person;
import todopal.model.Teacher;

@Service
public class ClassroomService {

	@Autowired
	ClassroomRepository classroomRepository;
	

	@Transactional
	public Classroom createClassroom(long id, Teacher teacher, String name, String imagePath, String subject) {
		Classroom classroom = new Classroom();

		classroom.setClassroomId(id);
		classroom.setTeacher(teacher);
		classroom.setName(name);
		classroom.setImagePath(imagePath);
		classroom.setSubject(subject);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomName(long id, String name) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.setName(name);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomDescription(long id, String description) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.setDescription(description);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomSubject(long id, String subject) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.setSubject(subject);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomImage(long id, String imagePath) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.setImagePath(imagePath);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskCategories(long id, ArrayList<String> taskCategories) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.setTaskCategories(taskCategories);

		classroomRepository.save(classroom);

		return classroom;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Classroom addToClassroomTaskCategories(long id, String taskCategory) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.getTaskCategories().add(taskCategory);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom setClassroomTaskTags(long id, ArrayList<String> taskTags) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.setTaskTags(taskTags);

		classroomRepository.save(classroom);

		return classroom;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Classroom addToClassroomTaskTag(long id, String taskTag) {
		Classroom classroom = classroomRepository.findByClassroomId(id);

		classroom.getTaskTags().add(taskTag);

		classroomRepository.save(classroom);

		return classroom;
	}

	@Transactional
	public Classroom getClassroom(long id) {
		return classroomRepository.findByClassroomId(id);
	}

	@Transactional
	public Iterable<Classroom> getAllClassrooms() {
		return toArrayList(classroomRepository.findAll());
	}

	private <T> ArrayList<T> toArrayList(Iterable<T> iterable){
		ArrayList<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	
	

}
