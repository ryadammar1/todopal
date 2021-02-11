package todopal.dto;

import javax.persistence.Id;

public class PersonDto {
	
	public PersonDto() {
	}
	
	public PersonDto(String name) {
		this.name= name;
	}
	
	public PersonDto(String name, String email, String password, String bio) {
		this.name= name;
		this.email= email;
		this.password= password;
		this.bio= bio;
	}
	
	
	private String name;

	public void setName(String value) {
	    this.name = value;
	}
	public String getName() {
	    return this.name;
	}
	private String email;

	public void setEmail(String value) {
	    this.email = value;
	}
	@Id
	public String getEmail() {
	    return this.email;
	}
	private String password;

	public void setPassword(String value) {
	    this.password = value;
	}
	public String getPassword() {
	    return this.password;
	}
	private String bio;

	public void setBio(String value) {
	    this.bio = value;
	}
	public String getBio() {
	    return this.bio;
	}
}
