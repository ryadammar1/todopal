package todopal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.StudentRepository;
import todopal.model.Student;
import todopal.model.TaskContainer;


@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Transactional
	public Student getStudent(String email) {
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Student email cannot be empty!");
		}
		return studentRepository.findStudentByEmail(email);
	}
	
	@Transactional
	public Student createStudent (String name, String email, String password) {
		Student student = new Student();
		if (name == null || name == "") {
			throw new IllegalArgumentException("Student cannot have an empty name");
		}
		if (email == null || email == "") {
			throw new IllegalArgumentException("Student cannot have an empty email");
		}
		if (password == null || password == "") {
			throw new IllegalArgumentException("Student cannot have an empty password");
		}
		
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email is used");
		}
		
		if(studentRepository.findStudentByEmail(email) != null) {
			throw new IllegalArgumentException("Already registered");
		}
		
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
			studentRepository.delete(student);;
			return true;
		}
		
		return false;
	}
	
	
	
	
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		iterable.forEach(resultList::add);
		return resultList;
	}

}
