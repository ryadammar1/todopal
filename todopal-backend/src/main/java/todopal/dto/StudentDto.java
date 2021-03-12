package todopal.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StudentDto {
	private String name;
	private String email;
	private String password;
	private String bio;
	private int totalPoints;
	private ArrayList<String> taskCategories;
	private ArrayList<String> taskTags;
	private Set<TaskContainerDto> schoolTask;
	private Set<TaskContainerDto> personalTask;
	private ClassroomDto classroom;

	public StudentDto(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.bio = null;
		this.totalPoints = 0;
		this.taskCategories = new ArrayList<String>();
		this.taskTags = new ArrayList<String>();
		this.schoolTask = new HashSet<TaskContainerDto>();
		this.personalTask = new HashSet<TaskContainerDto>();
	}
	
	public StudentDto(String name, String email, String password, String bio) {
		this.name = name;
		this.email = email;
		this.bio = bio;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	public void setTotalPoints(int value) {
		this.totalPoints = value;
	}

	public int getTotalPoints() {
		return this.totalPoints;
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

	public Set<TaskContainerDto> getSchoolTask() {
		return this.schoolTask;
	}

	public void setSchoolTask(Set<TaskContainerDto> schoolTasks) {
		this.schoolTask = schoolTasks;
	}

	public Set<TaskContainerDto> getPersonalTask() {
		return this.personalTask;
	}

	public void setPersonalTask(Set<TaskContainerDto> personalTasks) {
		this.personalTask = personalTasks;
	}

	public ClassroomDto getClassroom() {
		return this.classroom;
	}

	public void setClassroom(ClassroomDto classrooms) {
		this.classroom = classrooms;
	}
}
