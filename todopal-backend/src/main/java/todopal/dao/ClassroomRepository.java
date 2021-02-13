package todopal.dao;

import org.springframework.data.repository.CrudRepository;

import todopal.model.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, String> {

	Classroom findByClassroomId(long classId);

	Classroom findByNameAndTeacherEmail(String name, String teacherEmail);
	
}