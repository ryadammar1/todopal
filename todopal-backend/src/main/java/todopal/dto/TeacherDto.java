package todopal.dto;

import java.util.Set;

public class TeacherDto {

	private String name;
	private String email;
	private String password;
	private String bio;
	private String approvalCode;
	private Set<ClassroomDto> classroom;

	public TeacherDto(String name, String email, String password, String bio, String approvalCode) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.approvalCode = approvalCode;
	}

	public TeacherDto(String name, String email, String password, String bio) {
		this(name, email, password, bio, null);
	}

	public TeacherDto(String name) {
		this(name, null, null, null, null);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getBio() {
		return bio;
	}

	public void setApprovalCode(String value) {
		this.approvalCode = value;
	}

	public String getApprovalCode() {
		return this.approvalCode;
	}

	public Set<ClassroomDto> getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Set<ClassroomDto> classrooms) {
		this.classroom = classrooms;
	}
}
