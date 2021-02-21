package todopal.dao;

import org.springframework.data.repository.CrudRepository;

import todopal.model.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
	Student findStudentByEmail(String email);
}
