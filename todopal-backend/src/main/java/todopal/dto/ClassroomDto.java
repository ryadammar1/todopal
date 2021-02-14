package todopal.dto;

import java.util.ArrayList;

public class ClassroomDto {
	private long classroomId;
	private String classroomName;
	private String subject;
	private String description;
	private String imagePath;
	private TeacherDto teacher;
	private ArrayList<String> taskCategories;
	private ArrayList<String> taskTags;

	public ClassroomDto(long classroomId, String classroomName, String subject, String description, TeacherDto teacher,
			String imagePath) {
		this.classroomId = classroomId;
		this.classroomName = classroomName;
		this.subject = subject;
		this.description = description;
		this.imagePath = imagePath;
		this.teacher = teacher;
	}

	public ClassroomDto(long classroomId, String classroomName, String subject, TeacherDto teacher, String imagePath) {
		this(classroomId, classroomName, subject, null, teacher, imagePath);
	}

	public ClassroomDto(String classroomName) {
		this(0, classroomName, null, null, null, null);
	}

	public void setClassroomDtoId(long value) {
		this.classroomId = value;
	}

	public long getClassroomDtoId() {
		return this.classroomId;
	}

	public void setClassroomName(String value) {
		this.classroomName = value;
	}

	public String getClassroomName() {
		return this.classroomName;
	}

	public void setSubject(String value) {
		this.subject = value;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	public void setImagePath(String value) {
		this.imagePath = value;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public TeacherDto getTeacher() {
		return this.teacher;
	}

	public void setTeacher(TeacherDto teacher) {
		this.teacher = teacher;
	}

	public void setTaskCategories(ArrayList<String> value) {
		this.taskCategories = value;
	}

	public ArrayList<String> getTaskCategories() {
		return this.taskCategories;
	}

	public void setTaskTags(ArrayList<String> value) {
		this.taskTags = value;
	}

	public ArrayList<String> getTaskTags() {
		return this.taskTags;
	}
}
