package todopal.dto;

import java.util.ArrayList;
import java.util.Set;

public class ClassroomDto {
	
	private long classroomId;

	public ClassroomDto() {
	}

	public ClassroomDto(String name) {
		this.name = name;
	}

	public ClassroomDto(String name, TeacherDto teacher, String imagePath, String subject, long classroomId) {
		this.name = name;
		this.teacher = teacher;
		this.imagePath = imagePath;
		this.subject = subject;
		this.classroomId = classroomId;
	}

	public void setClassroomDtoId(long value) {
		this.classroomId = value;
	}

	public long getClassroomDtoId() {
		return this.classroomId;
	}
	private String name;

	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}
	private String description;

	public void setDescription(String value) {
		this.description = value;
	}
	public String getDescription() {
		return this.description;
	}
	private String imagePath;

	public void setImagePath(String value) {
		this.imagePath = value;
	}
	public String getImagePath() {
		return this.imagePath;
	}

	//TODO set string
	private ArrayList taskCategories;

	public void setTaskCategories(ArrayList value) {
		this.taskCategories = value;
	}
	public ArrayList getTaskCategories() {
		return this.taskCategories;
	}
	private ArrayList taskTags;

	public void setTaskTags(ArrayList value) {
		this.taskTags = value;
	}
	public ArrayList getTaskTags() {
		return this.taskTags;
	}
	private String subject;

	public void setSubject(String value) {
		this.subject = value;
	}
	public String getSubject() {
		return this.subject;
	}
	private TeacherDto teacher;


	public TeacherDto getTeacher() {
		return this.teacher;
	}

	public void setTeacher(TeacherDto teacher) {
		this.teacher = teacher;
	}

//	private Set<StudentDto> student;
//
//	public Set<StudentDto> getStudent() {
//		return this.student;
//	}
//
//	public void setStudent(Set<StudentDto> students) {
//		this.student = students;
//	}
//
//	private Set<TaskDto> task;
//
//	public Set<TaskDto> getTask() {
//		return this.task;
//	}
//
//	public void setTask(Set<TaskDto> tasks) {
//		this.task = tasks;
//	}

}
