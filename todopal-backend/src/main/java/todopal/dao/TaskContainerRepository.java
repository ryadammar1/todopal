package todopal.dao;

import org.springframework.data.repository.CrudRepository;
import todopal.model.TaskContainer;

public interface TaskContainerRepository extends CrudRepository<TaskContainer, Long> {
	TaskContainer findBytaskContainerId(long taskContainerId);
}
