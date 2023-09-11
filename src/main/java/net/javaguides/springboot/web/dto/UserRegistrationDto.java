package net.javaguides.springboot.web.dto;

import java.time.LocalDateTime;

public class UserRegistrationDto {

	private String firstName;
	private String lastName;

	public UserRegistrationDto(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	private String username;
	private String password;
	private String email;
	private String anrede;
	private LocalDateTime lastlogin;

	public LocalDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	private String firma;
	
	public UserRegistrationDto(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRegistrationDto(String firstName, String lastName, String username,
							   String password, String email, String firma, String anrede
							   ) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firma = firma;
		this.anrede=anrede;

	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

