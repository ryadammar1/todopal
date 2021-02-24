package todopal.dto;

import java.time.LocalDate;

import todopal.model.TaskStatus;

public class TaskContainerDto {
	private LocalDate completionDate;
	private TaskStatus status;
	private TaskDto task;
	private long taskContainerId;
	private String feedback;

	public TaskContainerDto(long id, LocalDate completionDate, TaskStatus status, TaskDto task, String feedback) {
		this.taskContainerId = id;
		this.completionDate = completionDate;
		this.status = status;
		this.task = task;
		this.feedback = feedback;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}
	
	public String getFeedback() {
		return this.feedback;
	}
	
	public LocalDate getCompletionDate() {
		return this.completionDate;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskStatus getStatus() {
		return this.status;
	}

	public void setTask(TaskDto task) {
		this.task = task;
	}

	public TaskDto getTask() {
		return this.task;
	}

	public void setTaskContainerId(long taskContainerId) {
		this.taskContainerId = taskContainerId;
	}

	public long getTaskContainerId() {
		return this.taskContainerId;
	}
}
