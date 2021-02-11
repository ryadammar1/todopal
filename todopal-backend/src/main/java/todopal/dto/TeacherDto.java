package todopal.dto;

import java.util.Set;


public class TeacherDto {
	
	public TeacherDto() {
	}
	private String name;
	private String email;
	private String password;
	private String bio;
	
	public TeacherDto(String name) {
		this.name= name;
	}
	
	public TeacherDto(String name, String email, String password, String bio) {
		this.name= name;
		this.email= email;
		this.password= password;
		this.bio= bio;
	}

	private String approvalCode;

	public void setApprovalCode(String value) {
		this.approvalCode = value;
	}
	public String getApprovalCode() {
		return this.approvalCode;
	}
	private Set<ClassroomDto> classroom;


	public Set<ClassroomDto> getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Set<ClassroomDto> classrooms) {
		this.classroom = classrooms;
	}
}
