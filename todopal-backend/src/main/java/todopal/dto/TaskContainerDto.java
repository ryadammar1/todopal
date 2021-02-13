package todopal.dto;

import java.sql.Date;
import java.time.LocalDate;

import todopal.model.TaskStatus;

public class TaskContainerDto {

	private LocalDate completionDate;
	private TaskStatus status;
	private TaskDto task;
	private long taskContainerId;
	
	
	public TaskContainerDto(long id, LocalDate completionDate, TaskStatus status, TaskDto task) {
		this.taskContainerId = id;
		this.completionDate = completionDate;
		this.status = status;
		this.task = task;
	}
	
	public LocalDate getCompletionDate() {
		return this.completionDate;
	}
	
	public TaskStatus getStatus() {
		return this.status;
	}

	public TaskDto getTask() {
		return this.task;
	}

	public long getTaskContainerId() {
		return this.taskContainerId;
	}
}
