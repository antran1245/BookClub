package com.antran.bookclub.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	
//	================= Variable =========================
	@NotEmpty(message="Email is required!")
	@Email(message="Email is invalid!")
	private String email;
	
	@NotEmpty(message="Password is required!")
	@Size(min=8, message="Password need to be at least 8 characters long.")
	private String password;
	
//	================== Constructor ========================
	
	public LoginUser() {}

//	================== Getter/Setter =======================

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
	
}
