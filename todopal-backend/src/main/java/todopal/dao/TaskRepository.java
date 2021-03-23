package todopal.dao;

import org.springframework.data.repository.CrudRepository;

import todopal.model.Classroom;
import todopal.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	Task findBytaskId(long taskId);
	Task deleteTaskByTaskId(long taskId);
	Task findTaskByNameAndAndClassroom(String name, Classroom classroom);
}
