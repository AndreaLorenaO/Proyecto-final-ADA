package com.ada.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupRequest {

	@NotNull
	@Size(min = 3, max = 20)
	private String username;

	@NotNull
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> role;
	// it is a set because the user can't have the same rol twice

	@NotNull
	@Size(min = 8, max = 16, message = "Password must be greater than 8 characters and less than 16 characters")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}
