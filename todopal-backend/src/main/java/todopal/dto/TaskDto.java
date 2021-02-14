package todopal.dto;

import java.time.LocalDate;

public class TaskDto {
	private long taskId;
	private String name;
	private String description;
	private String tag;
	private String category;
	private boolean isMandatory;
	private int pointCount;
	private LocalDate startDate;
	private LocalDate dueDate;
	private ClassroomDto classroom;

	public TaskDto(long taskId, String name, String description, String tag, String category, boolean isMandatory,
			int pointCount, LocalDate startDate, LocalDate dueDate, ClassroomDto classroom) {
		this.taskId = taskId;
		this.name = name;
		this.description = description;
		this.tag = tag;
		this.category = category;
		this.isMandatory = isMandatory;
		this.pointCount = pointCount;
		this.startDate = startDate;
		this.dueDate = dueDate;
	}

	public TaskDto(long taskId, String name, String description, String tag, String category, boolean isMandatory,
			int pointCount, LocalDate startDate, LocalDate dueDate) {
		this(taskId, name, description, tag, category, isMandatory, pointCount, startDate, dueDate, null);
	}

	public TaskDto(long taskId, String name, String description, String tag, String category, boolean isMandatory,
			int pointCount, ClassroomDto classroom) {
		this(taskId, name, description, tag, category, isMandatory, pointCount, null, null, classroom);
	}

	public TaskDto(long taskId, String name, String description, String tag, String category, boolean isMandatory,
			int pointCount) {
		this(taskId, name, description, tag, category, isMandatory, pointCount, null, null, null);
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getTaskId() {
		return this.taskId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean getMandatoryStatus() {
		return this.isMandatory;
	}

	public void setPointCount(int pointCount) {
		this.pointCount = pointCount;
	}

	public int getPointCount() {
		return pointCount;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setClassroom(ClassroomDto classroom) {
		this.classroom = classroom;
	}

	public ClassroomDto getClassroom() {
		return classroom;
	}
}
