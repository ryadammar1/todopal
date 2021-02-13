package todopal.dto;

import java.sql.Date;

public class TaskDto {

	private boolean isMandatory;
	private String tag;
	private String category;
	private int pointCount;
	private String name;
	private String description;
	private Date startDate;
	private Date dueDate;
	private long taskId;
	private ClassroomDto classroom;


	public TaskDto(long id, boolean isMandatory, String tag, String category, int pointCount, String name,
			String description, Date startDate, Date dueDate, ClassroomDto classroom) {
		this.taskId = id;
		this.isMandatory = isMandatory;
		this.tag = tag;
		this.category = category;
		this.pointCount = pointCount;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.classroom = classroom;	
	}
	
	public TaskDto(long id, boolean isMandatory, String tag, String category, int pointCount, String name,
			String description, Date startDate, Date dueDate) {
		this.taskId = id;
		this.isMandatory = isMandatory;
		this.tag = tag;
		this.category = category;
		this.pointCount = pointCount;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.classroom = null;	
	}
	
	public TaskDto(long id, boolean isMandatory, String tag, String category, int pointCount, String name,
			String description, ClassroomDto classroom) {
		this.taskId = id;
		this.isMandatory = isMandatory;
		this.tag = tag;
		this.category = category;
		this.pointCount = pointCount;
		this.name = name;
		this.description = description;
		this.startDate = null;
		this.dueDate = null;
		this.classroom = classroom;
	}
	
	public TaskDto(long id, boolean isMandatory, String tag, String category, int pointCount, String name,
			String description) {
		this.taskId = id;
		this.isMandatory = isMandatory;
		this.tag = tag;
		this.category = category;
		this.pointCount = pointCount;
		this.name = name;
		this.description = description;
		this.startDate = null;
		this.dueDate = null;
		this.classroom = null;
	}
	
	
	public boolean getIsMandatory() {
		return this.isMandatory;
	}

	public String getTag() {
		return this.tag;
	}

	public String getCategory() {
		return this.category;
	}


	public int getPointCount() {
		return this.pointCount;
	}

	public String getName() {
		return this.name;
	}


	public String getDescription() {
		return this.description;
	}


	public Date getStartDate() {
		return this.startDate;
	}


	public Date getDueDate() {
		return this.dueDate;
	}


	public long getTaskId() {
		return this.taskId;
	}



	public ClassroomDto getClassroom() {
		return this.classroom;
	}


}
