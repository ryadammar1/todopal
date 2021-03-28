package todopal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.ClassroomRepository;
import todopal.dao.StudentRepository;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.TaskContainer;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ClassroomRepository classroomRepository;

	@Transactional
	public Student getStudent(String email) {
		if (isEmptyString(email)) {
			throw new IllegalArgumentException("Student email cannot be empty!");
		} else if (studentRepository.findStudentByEmail(email) == null) {
			throw new IllegalArgumentException("Non-existant Student");
		}
		Student student = studentRepository.findStudentByEmail(email);
		if(student == null) {
			throw new IllegalArgumentException("Student does not exist!");
		}
		return student;
	}

	/**
	 * If the provided student's email and password match, the student is returned
	 * Otherwise, null is returned
	 * 
	 * @param email
	 * @param password
	 * @return student
	 * @throws IllegalArgumentException for empty email/password
	 */
	@Transactional
	public Student logInStudent(String email, String password) {
		if (isEmptyString(email))
			throw new IllegalArgumentException("Student email cannot be empty!");

		if (isEmptyString(password))
			throw new IllegalArgumentException("Student password cannot be empty!");

		Student student = studentRepository.findStudentByEmail(email);

		if (student == null) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (student.getPassword().equals(password))
			return student;
		else
			throw new IllegalArgumentException("Invalid password");
	}

	@Transactional
	public Student createStudent(String name, String email, String password) {
		if (isEmptyString(name)) {
			throw new IllegalArgumentException("Student cannot have an empty name");
		} else if (isEmptyString(email)) {
			throw new IllegalArgumentException("Student cannot have an empty email");
		} else if (isEmptyString(password)) {
			throw new IllegalArgumentException("Student cannot have an empty password");
		} else if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email is used");
		} else if (studentRepository.findStudentByEmail(email) != null) {
			throw new IllegalArgumentException("Already registered");
		}

		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		student.setTotalPoints(0);

		student.setPersonalTask(new HashSet<TaskContainer>());
		student.setSchoolTask(new HashSet<TaskContainer>());
		student.setTaskCategories(new ArrayList<String>());
		student.setTaskTags(new ArrayList<String>());

		studentRepository.save(student);
		return student;
	}

	@Transactional
	public Student createStudent(String name, String email, String password, int points) {
		if (isEmptyString(name)) {
			throw new IllegalArgumentException("Student cannot have an empty name");
		} else if (isEmptyString(email)) {
			throw new IllegalArgumentException("Student cannot have an empty email");
		} else if (isEmptyString(password)) {
			throw new IllegalArgumentException("Student cannot have an empty password");
		} else if (points < 0) {
			throw new IllegalArgumentException("Student cannot have an negative points");
		} else if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email is used");
		} else if (studentRepository.findStudentByEmail(email) != null) {
			throw new IllegalArgumentException("Already registered");
		}

		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		student.setTotalPoints(points);

		student.setPersonalTask(new HashSet<TaskContainer>());
		student.setSchoolTask(new HashSet<TaskContainer>());
		student.setTaskCategories(new ArrayList<String>());
		student.setTaskTags(new ArrayList<String>());

		studentRepository.save(student);
		return student;
	}

	@Transactional
	public List<Student> getAllStudents() {
		if (toList(studentRepository.findAll()).isEmpty()) {
			throw new IllegalArgumentException("There are no students registered!");
		}
		return toList(studentRepository.findAll());
	}

	@Transactional
	public boolean deleteStudent(String email) {
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Student email cannot be empty!");
		}

		Student student = studentRepository.findStudentByEmail(email);
		if (student != null) {
			studentRepository.delete(student);
			return true;
		}

		return false;
	}
	
	@Transactional
	public String getStudentName(String email) {
		Student student = getStudent(email);
		
		return student.getName();
	}
	
	@Transactional
	public Set<TaskContainer> getStudentPersonalTasks(String email) {
		Student student = getStudent(email);

		return student.getPersonalTask();
	}
	
	@Transactional
	public Set<TaskContainer> getStudentSchoolTasks(String email) {
		Student student = getStudent(email);
		if(student.getClassroom() == null){
			throw new IllegalStateException("Student is not part of a classroom");
		}
		return student.getSchoolTask();
	}

	@Transactional
	public boolean updateStudent(Student student) {
		if (studentRepository.findStudentByEmail(student.getEmail()) == null) {
			return false;
		} else {
			studentRepository.save(student);
			return true;
		}
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		iterable.forEach(resultList::add);
		return resultList;
	}

	private boolean isEmptyString(String value) {
		return (value == null || value.trim().length() == 0);
	}
	
}
