package todopal.dto;

import javax.persistence.Id;

public class PersonDto {
	private String name;
	private String email;
	private String password;
	private String bio;

	public PersonDto(String name, String email, String password, String bio) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.bio = bio;
	}

	public PersonDto(String name) {
		this(name, null, null, null);
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

	@Id
	public String getEmail() {
		return this.email;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	public void setBio(String value) {
		this.bio = value;
	}

	public String getBio() {
		return this.bio;
	}
}
