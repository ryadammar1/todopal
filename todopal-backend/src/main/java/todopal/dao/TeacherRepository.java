package todopal.dao;

import org.springframework.data.repository.CrudRepository;

import todopal.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, String> {
    Teacher findTeacherByemail(String email);
}
