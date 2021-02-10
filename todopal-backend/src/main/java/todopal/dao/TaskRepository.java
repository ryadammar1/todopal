package todopal.dao;

import org.springframework.data.repository.CrudRepository;

import todopal.model.Task;


public interface TaskRepository extends CrudRepository<Task, Long> {

	Task findBytaskId(long taskId);
	
}
